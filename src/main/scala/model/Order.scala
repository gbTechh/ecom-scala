package model

import doobie._
import doobie.implicits._
import doobie.implicits.javasql._
import java.sql.Timestamp

sealed trait OrderStatus
object OrderStatus {
  case object Pending extends OrderStatus
  case object Processing extends OrderStatus
  case object Shipped extends OrderStatus
  case object Delivered extends OrderStatus
  case object Cancelled extends OrderStatus

  implicit val orderStatusMeta: Meta[OrderStatus] =
    Meta[String].timap {
      case "Pending"    => Pending
      case "Processing" => Processing
      case "Shipped"    => Shipped
      case "Delivered"  => Delivered
      case "Cancelled"  => Cancelled
    } {
      case Pending    => "Pending"
      case Processing => "Processing"
      case Shipped    => "Shipped"
      case Delivered  => "Delivered"
      case Cancelled  => "Cancelled"
    }
}

case class Order(
    id: Int,
    userId: Int,
    totalAmount: Double,
    status: OrderStatus,
    createdAt: Timestamp,
    updatedAt: Timestamp,
    deletedAt: Option[Timestamp]
)

object Order {
  implicit val orderRead: Read[Order] =
    Read[(Int, Int, Double, String, Timestamp, Timestamp, Option[Timestamp])]
      .map { case (id, userId, amount, status, created, updated, deleted) =>
        Order(
          id,
          userId,
          amount,
          OrderStatus.orderStatusMeta.get(status),
          created,
          updated,
          deleted
        )
      }
}
