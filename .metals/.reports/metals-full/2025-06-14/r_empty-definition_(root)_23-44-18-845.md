error id: file://<WORKSPACE>/src/main/scala/repository/CategoryRepository.scala:
file://<WORKSPACE>/src/main/scala/repository/CategoryRepository.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 278
uri: file://<WORKSPACE>/src/main/scala/repository/CategoryRepository.scala
text:
```scala
package repository

import cats.effect.IO
import doobie.implicits._
import doobie.util.transactor.Transactor
// Imports necesarios para Instant
import doobie.implicits.javasql._
import doobie.implicits.javatime._
// Import para derivación automática
import doobie.generic.auto._@@
import model.Category

trait CategoryRepository {
  def findById(id: Int): IO[Option[Category]]
  def create(category: Category): IO[Unit]
  def update(category: Category): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object CategoryRepository {
  def apply(xa: Transactor[IO]): CategoryRepository = new CategoryRepository {
    def findById(id: Int): IO[Option[Category]] =
      sql"SELECT id, name, slug, description, status, created_at, updated_at, deleted_at FROM categories WHERE id = $id AND deleted_at IS NULL"
        .query[Category]
        .option
        .transact(xa)

    def create(category: Category): IO[Unit] =
      sql"""
        INSERT INTO categories (id, name, slug, description, status, created_at, updated_at, deleted_at)
        VALUES (${category.id}, ${category.name}, ${category.slug}, ${category.description}, ${category.status}, ${category.createdAt}, ${category.updatedAt}, ${category.deletedAt})
      """.update.run
        .transact(xa)
        .void

    def update(category: Category): IO[Unit] =
      sql"""
        UPDATE categories
        SET name = ${category.name}, slug = ${category.slug}, description = ${category.description}, status = ${category.status},
            updated_at = ${category.updatedAt}, deleted_at = ${category.deletedAt}
        WHERE id = ${category.id}
      """.update.run
        .transact(xa)
        .void

    def delete(id: Int): IO[Unit] =
      sql"UPDATE categories SET deleted_at = NOW() WHERE id = $id".update.run
        .transact(xa)
        .void
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 