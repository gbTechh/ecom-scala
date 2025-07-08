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

  implicit val ordeStatusMeta: Meta[OrderStatus] = {
    val fromString: String => OrderStatus = {
      case "Pending"    => Pending
      case "Processing" => Processing
      case "Shipped"    => Shipped
      case "Delivered"  => Delivered
      case "Cancelled"  => Cancelled
      case other =>
        throw new IllegalArgumentException(s"Unknown OrderStatus: $other")
    }

    val toString: OrderStatus => String = {
      case Pending    => "Pending"
      case Processing => "Processing"
      case Shipped    => "Shipped"
      case Delivered  => "Delivered"
      case Cancelled  => "Cancelled"
    }

    Meta[String].timap(fromString)(toString)
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
  implicit val orderRead: Read[Order] = {
    // Asegurarse de que todas las instancias necesarias estén disponibles
    implicit val orderMeta: Meta[OrderStatus] = OrderStatus.ordeStatusMeta

    Read[
      (Int, Int, Double, OrderStatus, Timestamp, Timestamp, Option[Timestamp])
    ]
      .map { case (id, userId, amount, status, created, updated, deleted) =>
        Order(
          id,
          userId,
          amount,
          status,
          created,
          updated,
          deleted
        )
      }
  }
}
