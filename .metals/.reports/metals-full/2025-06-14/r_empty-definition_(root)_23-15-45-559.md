error id: file://<WORKSPACE>/src/main/scala/model/Category.scala:
file://<WORKSPACE>/src/main/scala/model/Category.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 362
uri: file://<WORKSPACE>/src/main/scala/model/Category.scala
text:
```scala
package model

import doobie._
import doobie.implicits._
import java.sql.Timestamp

final case class Category(
    id: Int,
    name: String,
    slug: String,
    description: Option[String],
    status: Boolean,
    createdAt: Timestamp,
    updatedAt: Timestamp,
    deletedAt: Option[Timestamp]
)

// En tu objeto companion de Category
object Category {
  im@@port doobie.postgres.implicits._ // Si usas PostgreSQL
  import doobie.util.meta.Meta

  // Esto ayuda con la conversión Boolean <-> TinyInt(1)
  implicit val booleanMeta: Meta[Boolean] = 
    Meta[Int].timap(_ == 1)(if (_) 1 else 0)

  // Instancias derivadas automáticamente (requiere doobie 1.x)
  implicit val readCategory: Read[Category] = Read.derived
  implicit val writeCategory: Write[Category] = Write.derived
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 