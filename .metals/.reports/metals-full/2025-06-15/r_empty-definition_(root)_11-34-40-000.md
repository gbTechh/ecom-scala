error id: file://<WORKSPACE>/src/main/scala/model/Product.scala:
file://<WORKSPACE>/src/main/scala/model/Product.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 636
uri: file://<WORKSPACE>/src/main/scala/model/Product.scala
text:
```scala
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

object Category {
  // Derivación automática @@de Read para Category
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

```


#### Short summary: 

empty definition using pc, found symbol in pc: 