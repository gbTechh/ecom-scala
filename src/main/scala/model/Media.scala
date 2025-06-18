package model

import doobie._
import doobie.implicits._
import doobie.implicits.javasql._
import java.sql.Timestamp

case class Media(
    id: Int,
    productId: Int, // id_product
    filePath: String,
    fileType: Option[String], // varchar(50) opcional
    isPrimary: Boolean, // tinyint(1) mapeado a Boolean
    createdAt: java.sql.Timestamp,
    updatedAt: java.sql.Timestamp,
    deletedAt: Option[java.sql.Timestamp]
)

object Media {
  implicit val mediaRead: Read[Media] =
    Read[
      (
        Int,                 // id
        Int,                 // product_id
        String,              // file_path
        Option[String],      // file_type
        Boolean,             // is_primary
        Timestamp,           // created_at
        Timestamp,           // updated_at
        Option[Timestamp]    // deleted_at
      )
    ].map {
      case (id, prodId, path, fType, primary, created, updated, deleted) =>
        Media(id, prodId, path, fType, primary, created, updated, deleted)
    }
}