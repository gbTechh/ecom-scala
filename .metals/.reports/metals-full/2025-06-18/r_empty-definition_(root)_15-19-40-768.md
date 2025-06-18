error id: file://<WORKSPACE>/src/main/scala/service/CategoryService.scala:local7
file://<WORKSPACE>/src/main/scala/service/CategoryService.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol local7
empty definition using fallback
non-local guesses:

offset: 692
uri: file://<WORKSPACE>/src/main/scala/service/CategoryService.scala
text:
```scala
package service

import cats.effect.IO
import cats.syntax.all._
import model.Category
import repository.CategoryRepository

trait CategoryService {
  def findAll(): IO[Option[Category]]
  def findById(id: Int): IO[Option[Category]]
  def create(category: Category): IO[Unit]
  def update(category: Category): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object CategoryService {
  def apply(repo: CategoryRepository): CategoryService = new CategoryService {
    def findAll(): IO[Option[Category]] =
      repo.findAll()
    def findById(id: Int): IO[Option[Category]] =
      repo.findById(id)

    def create(category: Category): IO[Unit] =
      for {
        _ <- validateCategory(category@@)
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

```


#### Short summary: 

empty definition using pc, found symbol in pc: 