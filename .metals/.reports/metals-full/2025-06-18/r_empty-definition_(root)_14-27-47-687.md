error id: file://<WORKSPACE>/src/main/scala/http/controllers/CategoryController.scala:
file://<WORKSPACE>/src/main/scala/http/controllers/CategoryController.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -org/http4s/dsl/io/CategoryView.
	 -CategoryView.
	 -scala/Predef.CategoryView.
offset: 417
uri: file://<WORKSPACE>/src/main/scala/http/controllers/CategoryController.scala
text:
```scala
package http.controllers

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import service.CategoryService
import http.controllers.CategoryController

object CategoryController {
  def apply(service: CategoryService): HttpRoutes[IO] = {
    HttpRoutes.of[IO] { case GET -> Root / "categories" / IntVar(id) =>
      service.findById(id).flatMap {
        case Some(category) => Ok(CategoryV@@iew.renderCategory(category))
        case None           => NotFound(s"Categoría con ID $id no encontrada")
      }
    }
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 