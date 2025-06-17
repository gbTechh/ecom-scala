error id: file://<WORKSPACE>/src/main/scala/repository/ProductRepository.scala:
file://<WORKSPACE>/src/main/scala/repository/ProductRepository.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 615
uri: file://<WORKSPACE>/src/main/scala/repository/ProductRepository.scala
text:
```scala
package repository

import cats.effect.IO
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
import model.Product
import model.Product._ // Importa las instancias implícitas

trait ProductRepository {
  def findById(id: Int): IO[Option[Product]]
  def create(product: Product): IO[Unit]
  def update(product: Product): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object ProductRepository {
  def apply(xa: Transactor[IO]): ProductRepository = new ProductRepository {

    def findById(id: Int): IO[Option[Category]] = {
      sql"""
        SELECT id, name, slug, description, status@@, created_at, updated_at, deleted_at 
        FROM categories 
        WHERE id = $id AND deleted_at IS NULL
      """.query[Category].option.transact(xa)
    }

    def create(category: Category): IO[Unit] = {
      sql"""
        INSERT INTO categories (name, slug, description, status)
        VALUES (${category.name}, ${category.slug}, ${category.description}, ${category.status})
      """.update.run.transact(xa).void
    }

    def update(category: Category): IO[Unit] = {
      sql"""
        UPDATE categories
        SET name = ${category.name}, 
            slug = ${category.slug}, 
            description = ${category.description}, 
            status = ${category.status},
            updated_at = NOW()
        WHERE id = ${category.id}
      """.update.run.transact(xa).void
    }

    def delete(id: Int): IO[Unit] = {
      sql"UPDATE categories SET deleted_at = NOW() WHERE id = $id".update.run
        .transact(xa)
        .void
    }
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 