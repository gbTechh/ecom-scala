package repository

import cats.effect.IO
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
import model.Product
import model.Product._ // Importa las instancias implícitas

trait ProductRepository {
  def findAll(): IO[List[Product]]
  def findById(id: Int): IO[Option[Product]]
  def findBySlug(slug: String): IO[Option[Product]]
  def findByCategorySlug(categorySlug: String): IO[List[Product]]
  def searchByName(term: String): IO[List[Product]]
  def create(product: Product): IO[Unit]
  def update(product: Product): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object ProductRepository {
  def apply(xa: Transactor[IO]): ProductRepository = new ProductRepository {
    def findByCategorySlug(categorySlug: String): IO[List[Product]] = {
      sql"""
        SELECT p.* 
        FROM products p
        JOIN categories c ON p.id_category = c.id
        WHERE c.slug = $categorySlug AND p.deleted_at IS NULL
      """.query[Product].to[List].transact(xa)
    }
    def searchByName(term: String): IO[List[Product]] = {
      val searchTerm = s"%${term.toLowerCase}%"
      sql"""
        SELECT * 
        FROM products 
        WHERE LOWER(name) LIKE $searchTerm 
        AND deleted_at IS NULL
      """.query[Product].to[List].transact(xa)
    }
    def findAll(): IO[List[Product]] =
      sql"""
        SELECT *
        FROM products
        WHERE deleted_at IS NULL
      """.query[Product].to[List].transact(xa)

    def findById(id: Int): IO[Option[Product]] = {
      sql"""
        SELECT id, name, slug, description, status, stock, price, id_category, created_at, updated_at, deleted_at 
        FROM products 
        WHERE id = $id AND deleted_at IS NULL
      """.query[Product].option.transact(xa)
    }

    def findBySlug(slug: String): IO[Option[Product]] = {
      sql"""
        SELECT id, name, slug, description, status, stock, price, id_category, created_at, updated_at, deleted_at 
        FROM products 
        WHERE slug = $slug AND deleted_at IS NULL
      """.query[Product].option.transact(xa)
    }

    def create(product: Product): IO[Unit] = {
      sql"""
        INSERT INTO products (name, slug, description, status, stock, price, id_category)
        VALUES (${product.name}, ${product.slug}, ${product.description}, ${product.status}, ${product.stock}, ${product.price}, ${product.categoryId})
      """.update.run.transact(xa).void
    }

    def update(product: Product): IO[Unit] = {
      sql"""
        UPDATE products
        SET name = ${product.name}, 
            slug = ${product.slug}, 
            description = ${product.description}, 
            status = ${product.status}, 
            stock = ${product.stock}, 
            price = ${product.price}, 
            id_category = ${product.categoryId}, 
            updated_at = NOW()
        WHERE id = ${product.id}
      """.update.run.transact(xa).void
    }

    def delete(id: Int): IO[Unit] = {
      sql"UPDATE products SET deleted_at = NOW() WHERE id = $id".update.run
        .transact(xa)
        .void
    }
  }
}
