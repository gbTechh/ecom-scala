file://<WORKSPACE>/src/main/scala/service/ProductService.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
empty definition using fallback
non-local guesses:

offset: 1055
uri: file://<WORKSPACE>/src/main/scala/service/ProductService.scala
text:
```scala
package service

import cats.effect.IO
import cats.syntax.all._
import model.Category
import repository.CategoryRepository

trait ProductService {
  def findAll(): IO[List[Product]]
  def findById(id: Int): IO[Option[Product]]
  def create(product: Product): IO[Unit]
  def update(product: Product): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object ProductService {
  def apply(repo: ProductRepository): ProductService = new ProductService {
    def findAll(): IO[List[Product]] =
      repo.findAll()
    def findById(id: Int): IO[Option[Product]] =
      repo.findById(id)

    def create(product: Product): IO[Unit] =
      for {
        _ <- validateProduct(product)
        _ <- repo.create(product)
      } yield ()

    def update(product: Product): IO[Unit] =
      for {
        _ <- validateProduct(product)
        _ <- repo.update(product)
      } yield ()

    def delete(id: Int): IO[Unit] =
      repo.delete(id)

    private def validateProduct(category: Product): IO[Unit] =
      IO.raiseError(
        new IllegalArgumentException("Ca@@tegory name cannot be empty")
      ).whenA(category.name.isEmpty)
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 