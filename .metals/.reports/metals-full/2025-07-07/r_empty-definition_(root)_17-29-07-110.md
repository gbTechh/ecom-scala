error id: file://<WORKSPACE>/src/main/scala/model/Order.scala:java/sql/Timestamp#
file://<WORKSPACE>/src/main/scala/model/Order.scala
empty definition using pc, found symbol in pc: java/sql/Timestamp#
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -doobie/Timestamp#
	 -doobie/implicits/Timestamp#
	 -java/sql/Timestamp#
	 -Timestamp#
	 -scala/Predef.Timestamp#
offset: 630
uri: file://<WORKSPACE>/src/main/scala/model/Order.scala
text:
```scala
package model

import doobie._
import doobie.implicits._
import java.sql.Timestamp

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
    implicit val timestampMeta: Meta[Timestamp] = Meta[java.sql.Timestamp]
    implicit val optionTimestampMeta: Meta[Option[Timestamp]] =
      Meta[Option[java.sql.Timestamp]]

    Read[(Int, Int, Double, String, Timestamp, Ti@@mestamp, Option[Timestamp])]
      .map { case (id, userId, amount, status, created, updated, deleted) =>
        Order(
          id,
          userId,
          amount,
          OrderStatus.fromString(status),
          created,
          updated,
          deleted
        )
      }
  }
}

sealed trait OrderStatus
object OrderStatus {
  case object Pending extends OrderStatus
  case object Processing extends OrderStatus
  case object Shipped extends OrderStatus
  case object Delivered extends OrderStatus
  case object Cancelled extends OrderStatus

  implicit val orderStatusMeta: Meta[OrderStatus] =
    Meta[String].timap(fromString)(toString)

  def fromString(s: String): OrderStatus = s match {
    case "Pending"    => Pending
    case "Processing" => Processing
    case "Shipped"    => Shipped
    case "Delivered"  => Delivered
    case "Cancelled"  => Cancelled
    case other =>
      throw new IllegalArgumentException(s"Unknown OrderStatus: $other")
  }

  def toString(status: OrderStatus): String = status match {
    case Pending    => "Pending"
    case Processing => "Processing"
    case Shipped    => "Shipped"
    case Delivered  => "Delivered"
    case Cancelled  => "Cancelled"
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/sql/Timestamp#