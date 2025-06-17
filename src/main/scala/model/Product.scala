package model

import doobie._
import doobie.implicits._
import doobie.implicits.javasql._ // CRÍTICO: Para soporte de java.sql.Timestamp
import java.sql.Timestamp

case class Product(
    id: Int,
    name: String,
    slug: String,
    description: Option[String], // text es opcional
    status: Option[Boolean], // tinyint(1) opcional
    stock: Option[Int], // int opcional
    price: Option[Float], // float opcional
    categoryId: Option[Int], // id_category opcional
    createdAt: java.sql.Timestamp,
    updatedAt: java.sql.Timestamp,
    deletedAt: Option[java.sql.Timestamp]
)

object Product {
  // Derivación automática de Read para Product
  implicit val categoryRead: Read[Product] =
    Read[
      (
          Int,
          String,
          String,
          Option[String],
          Option[Boolean],
          Option[Int],
          Option[Float],
          Option[Int],
          Timestamp,
          Timestamp,
          Option[Timestamp]
      )
    ]
      .map {
        case (
              id,
              name,
              slug,
              desc,
              status,
              stock,
              price,
              categoryId,
              created,
              updated,
              deleted
            ) =>
          Product(
            id,
            name,
            slug,
            desc,
            status,
            stock,
            price,
            categoryId,
            created,
            updated,
            deleted
          )
      }
}
