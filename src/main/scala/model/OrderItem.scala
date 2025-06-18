package model

import doobie._
import doobie.implicits._
import doobie.implicits.javasql._
import java.sql.Timestamp

case class OrderItem(
    id: Int,
    orderId: Int,
    productId: Int,
    quantity: Int,
    unitPrice: Float,
    createdAt: Timestamp,
    updatedAt: Timestamp
)

object OrderItem {
  implicit val orderItemRead: Read[OrderItem] =
    Read[(Int, Int, Int, Int, Float, Timestamp, Timestamp)].map {
      case (id, orderId, productId, quantity, unitPrice, createdAt, updatedAt) =>
        OrderItem(id, orderId, productId, quantity, unitPrice, createdAt, updatedAt)
    }
}
