package service

import cats.effect.IO
import cats.syntax.all._
import model.Category
import repository.CategoryRepository

trait CategoryService {
  def findAll(): IO[List[Category]]
  def findById(id: Int): IO[Option[Category]]
  def create(category: Category): IO[Unit]
  def update(category: Category): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object CategoryService {
  def apply(repo: CategoryRepository): CategoryService = new CategoryService {
    def findAll(): IO[List[Category]] =
      repo.findAll()
    def findById(id: Int): IO[Option[Category]] =
      repo.findById(id)

    def create(category: Category): IO[Unit] =
      for {
        _ <- validateCategory(category)
        _ <- repo.create(category)
      } yield ()

    def update(category: Category): IO[Unit] =
      for {
        _ <- validateCategory(category)
        _ <- repo.update(category)
      } yield ()

    def delete(id: Int): IO[Unit] =
      repo.delete(id)

    private def validateCategory(category: Category): IO[Unit] =
      IO.raiseError(
        new IllegalArgumentException("Category name cannot be empty")
      ).whenA(category.name.isEmpty)
  }
}
