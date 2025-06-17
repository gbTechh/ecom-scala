package repository

import cats.effect.IO
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
import model.Category
import model.Category._ // Importa las instancias implícitas

trait CategoryRepository {
  def findById(id: Int): IO[Option[Category]]
  def findBySlug(slug: String): IO[Option[Category]]
  def create(category: Category): IO[Unit]
  def update(category: Category): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object CategoryRepository {
  def apply(xa: Transactor[IO]): CategoryRepository = new CategoryRepository {

    def findById(id: Int): IO[Option[Category]] = {
      sql"""
        SELECT id, name, slug, description, status, created_at, updated_at, deleted_at 
        FROM categories 
        WHERE id = $id AND deleted_at IS NULL
      """.query[Category].option.transact(xa)
    }
    def findBySlug(slug: String): IO[Option[Category]] = {
      sql"""
        SELECT id, name, slug, description, status, created_at, updated_at, deleted_at 
        FROM categories 
        WHERE slug = $slug AND deleted_at IS NULL
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
