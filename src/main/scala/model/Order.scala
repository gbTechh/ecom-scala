package model

sealed trait OrderStatus
object OrderStatus {
  case object Pending extends OrderStatus
  case object Processing extends OrderStatus
  case object Shipped extends OrderStatus
  case object Delivered extends OrderStatus
  case object Cancelled extends OrderStatus
}

case class Order(
    id: Int,
    userId: Int,
    totalAmount: Float,
    status: OrderStatus,
    createdAt: java.sql.Timestamp,
    updatedAt: java.sql.Timestamp,
    deletedAt: Option[java.sql.Timestamp]
)
