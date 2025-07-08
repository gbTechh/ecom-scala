file://<WORKSPACE>/src/main/scala/service/ProductService.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
empty definition using fallback
non-local guesses:
	 -cats/syntax/all/category.
	 -cats/syntax/all/category#
	 -cats/syntax/all/category().
	 -category.
	 -category#
	 -category().
	 -scala/Predef.category.
	 -scala/Predef.category#
	 -scala/Predef.category().
offset: 664
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
        _ <- validateProduct(@@category)
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