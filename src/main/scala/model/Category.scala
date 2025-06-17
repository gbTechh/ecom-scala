package model

import doobie._
import doobie.implicits._
import doobie.implicits.javasql._ // CRÍTICO: Para soporte de java.sql.Timestamp
import java.sql.Timestamp

case class Category(
    id: Int,
    name: String,
    slug: String,
    description: Option[String],
    status: Boolean,
    createdAt: Timestamp,
    updatedAt: Timestamp,
    deletedAt: Option[Timestamp]
)

object Category {
  // Derivación automática de Read para Category
  implicit val categoryRead: Read[Category] =
    Read[
      (
          Int,
          String,
          String,
          Option[String],
          Boolean,
          Timestamp,
          Timestamp,
          Option[Timestamp]
      )
    ]
      .map { case (id, name, slug, desc, status, created, updated, deleted) =>
        Category(id, name, slug, desc, status, created, updated, deleted)
      }
}
