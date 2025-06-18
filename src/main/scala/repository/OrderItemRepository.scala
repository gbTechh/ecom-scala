package repository

import cats.effect.IO
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
import model.OrderItem
import model.OrderItem._

trait OrderItemRepository {
  def findById(id: Int): IO[Option[OrderItem]]
  def findByOrderId(orderId: Int): IO[List[OrderItem]]
  def create(orderItem: OrderItem): IO[Unit]
  def update(orderItem: OrderItem): IO[Unit]
  def delete(id: Int): IO[Unit]
  def deleteByOrderId(orderId: Int): IO[Unit]
}

object OrderItemRepository {
  def apply(xa: Transactor[IO]): OrderItemRepository = new OrderItemRepository {

    def findById(id: Int): IO[Option[OrderItem]] = {
      sql"""
        SELECT id, order_id, product_id, quantity, unit_price, created_at, updated_at 
        FROM order_items 
        WHERE id = $id
      """.query[OrderItem].option.transact(xa)
    }

    def findByOrderId(orderId: Int): IO[List[OrderItem]] = {
      sql"""
        SELECT id, order_id, product_id, quantity, unit_price, created_at, updated_at 
        FROM order_items 
        WHERE order_id = $orderId
      """.query[OrderItem].to[List].transact(xa)
    }

    def create(orderItem: OrderItem): IO[Unit] = {
      sql"""
        INSERT INTO order_items (order_id, product_id, quantity, unit_price)
        VALUES (${orderItem.orderId}, ${orderItem.productId}, ${orderItem.quantity}, ${orderItem.unitPrice})
      """.update.run.transact(xa).void
    }

    def update(orderItem: OrderItem): IO[Unit] = {
      sql"""
        UPDATE order_items
        SET order_id = ${orderItem.orderId}, 
            product_id = ${orderItem.productId}, 
            quantity = ${orderItem.quantity}, 
            unit_price = ${orderItem.unitPrice},
            updated_at = NOW()
        WHERE id = ${orderItem.id}
      """.update.run.transact(xa).void
    }

    def delete(id: Int): IO[Unit] = {
      sql"DELETE FROM order_items WHERE id = $id".update.run
        .transact(xa)
        .void
    }

    def deleteByOrderId(orderId: Int): IO[Unit] = {
      sql"DELETE FROM order_items WHERE order_id = $orderId".update.run
        .transact(xa)
        .void
    }
  }
}