file://<WORKSPACE>/src/main/scala/service/ProductService.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
empty definition using fallback
non-local guesses:

offset: 558
uri: file://<WORKSPACE>/src/main/scala/service/ProductService.scala
text:
```scala
package service

import cats.effect.IO
import cats.syntax.all._
import model.Product
import repository.ProductRepository

trait ProductService {
  def findAll(): IO[List[Product]]
  def findById(id: Int): IO[Option[Product]]
  def findBySlug(slug: String): IO[Option[Product]]
  def create(product: Product): IO[Unit]
  def update(product: Product): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object ProductService {
  def apply(repo: ProductRepository): ProductService = new ProductService {
    def findAll(): IO[List[Product]] =
      repo.findAll()
    @@def findById(id: Int): IO[Option[Product]] =
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

    private def validateProduct(product: Product): IO[Unit] =
      IO.raiseError(
        new IllegalArgumentException("Product name cannot be empty")
      ).whenA(product.name.isEmpty)
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 