error id: file://<WORKSPACE>/src/main/scala/service/OrderService.scala:java/lang/IllegalStateException#
file://<WORKSPACE>/src/main/scala/service/OrderService.scala
empty definition using pc, found symbol in pc: java/lang/IllegalStateException#
semanticdb not found
empty definition using fallback
non-local guesses:
	 -cats/syntax/all/IllegalStateException#
	 -IllegalStateException#
	 -scala/Predef.IllegalStateException#
offset: 3710
uri: file://<WORKSPACE>/src/main/scala/service/OrderService.scala
text:
```scala
package service

import cats.effect.IO
import cats.syntax.all._
import model.{Order, OrderItem, OrderStatus}
import repository.{OrderRepository, OrderItemRepository}
import java.sql.Timestamp
import java.time.Instant

trait OrderService {
  def findById(id: Int): IO[Option[Order]]
  def findByUserId(userId: Int): IO[List[Order]]
  def getOrderWithItems(orderId: Int): IO[Option[(Order, List[OrderItem])]]
  def createOrder(order: Order, items: List[OrderItem]): IO[Unit]
  def updateOrderStatus(orderId: Int, status: OrderStatus): IO[Unit]
  def cancelOrder(orderId: Int): IO[Unit]
}

object OrderService {
  def apply(
      orderRepo: OrderRepository,
      orderItemRepo: OrderItemRepository
  ): OrderService = new OrderService {

    def findById(id: Int): IO[Option[Order]] =
      orderRepo.findById(id)

    def findByUserId(userId: Int): IO[List[Order]] =
      orderRepo.findByUserId(userId)

    def getOrderWithItems(
        orderId: Int
    ): IO[Option[(Order, List[OrderItem])]] = {
      for {
        order <- orderRepo.findById(orderId)
        items <- order.fold(IO.pure(List.empty[OrderItem]))(o =>
          orderItemRepo.findByOrderId(o.id)
        )
      } yield order.map(_ -> items)
    }

    def createOrder(order: Order, items: List[OrderItem]): IO[Unit] = {
      for {
        _ <- validateOrder(order)
        _ <- validateOrderItems(items)
        _ <- orderRepo.create(order)
        // Necesitaríamos obtener el ID de la orden recién creada
        // Esto asume que order.id es generado por la base de datos
        createdOrder <- orderRepo.findByUserId(order.userId).map(_.headOption)
        _ <- createdOrder match {
          case Some(o) =>
            items.traverse_(item =>
              orderItemRepo.create(item.copy(orderId = o.id))
            )
          case None =>
            IO.raiseError(
              new IllegalStateException("Failed to retrieve created order")
            )
        }
      } yield ()
    }

    def updateOrderStatus(orderId: Int, status: OrderStatus): IO[Unit] = {
      for {
        _ <- validateStatusTransition(orderId, status)
        _ <- orderRepo.updateStatus(orderId, status)
      } yield ()
    }

    def cancelOrder(orderId: Int): IO[Unit] = {
      updateOrderStatus(orderId, OrderStatus.Cancelled)
    }

    // Métodos de validación privados
    private def validateOrder(order: Order): IO[Unit] = {
      IO.raiseError(new IllegalArgumentException("Order must have a user ID"))
        .whenA(order.userId <= 0) >>
        IO.raiseError(
          new IllegalArgumentException("Order total amount must be positive")
        ).whenA(order.totalAmount <= 0)
    }

    private def validateOrderItems(items: List[OrderItem]): IO[Unit] = {
      IO.raiseError(
        new IllegalArgumentException("Order must have at least one item")
      ).whenA(items.isEmpty) >>
        items.traverse_ { item =>
          IO.raiseError(
            new IllegalArgumentException("Item quantity must be positive")
          ).whenA(item.quantity <= 0) >>
            IO.raiseError(
              new IllegalArgumentException("Item unit price must be positive")
            ).whenA(item.unitPrice <= 0)
        }
    }

    private def validateStatusTransition(
        orderId: Int,
        newStatus: OrderStatus
    ): IO[Unit] = {
      orderRepo.findById(orderId).flatMap {
        case Some(order) =>
          (order.status, newStatus) match {
            case (OrderStatus.Cancelled, _) =>
              IO.raiseError(
                new IllegalStateException("Cannot modify a cancelled order")
              )
            case (_, OrderStatus.Pending) =>
              IO.raiseError(
                new IllegalStateEx@@ception("Cannot revert to Pending status")
              )
            case (OrderStatus.Delivered, OrderStatus.Shipped) =>
              IO.raiseError(
                new IllegalStateException(
                  "Cannot revert from Delivered to Shipped"
                )
              )
            case _ => IO.unit
          }
        case None =>
          IO.raiseError(new IllegalArgumentException("Order not found"))
      }
    }
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/lang/IllegalStateException#