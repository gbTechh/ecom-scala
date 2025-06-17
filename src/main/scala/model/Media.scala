package model

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
