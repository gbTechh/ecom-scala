package service

import cats.effect.IO
import model.OrderItem
import repository.OrderItemRepository

trait OrderItemService {
  def findById(id: Int): IO[Option[OrderItem]]
  def findByOrderId(orderId: Int): IO[List[OrderItem]]
  def create(orderItem: OrderItem): IO[Unit]
  def update(orderItem: OrderItem): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object OrderItemService {
  def apply(repo: OrderItemRepository): OrderItemService = new OrderItemService {
    def findById(id: Int): IO[Option[OrderItem]] = repo.findById(id)
    def findByOrderId(orderId: Int): IO[List[OrderItem]] = repo.findByOrderId(orderId)
    def create(orderItem: OrderItem): IO[Unit] = for {
      _ <- validateOrderItem(orderItem)
      _ <- repo.create(orderItem)
    } yield ()
    def update(orderItem: OrderItem): IO[Unit] = for {
      _ <- validateOrderItem(orderItem)
      _ <- repo.update(orderItem)
    } yield ()
    def delete(id: Int): IO[Unit] = repo.delete(id)

    private def validateOrderItem(orderItem: OrderItem): IO[Unit] =
      IO.raiseError(new IllegalArgumentException("OrderItem quantity must be positive"))
        .whenA(orderItem.quantity <= 0)
  }
}