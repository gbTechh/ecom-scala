error id: file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala:
file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 69
uri: file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
text:
```scala
package http.controllers

import cats.effect.IO
import org.http4s._
i@@mport org.http4s.dsl.io._
import org.http4s.headers.Location
import http.views.CategoryAdminView
import service.CategoryService

class CategoryAdminController(service: CategoryService) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {

    case GET -> Root / "admin" / "category" =>
      for {
        cats <- service.findAll() // Esto devuelve List[Category]
        res <- Ok(CategoryAdminView.list(cats))
      } yield res.withContentType(
        org.http4s.headers.`Content-Type`(org.http4s.MediaType.text.html)
      )

    case GET -> Root / "admin" / "category" / "add" =>
      Ok(CategoryAdminView.addForm())

    case GET -> Root / "admin" / "category" / "edit" / IntVar(id) =>
      for {
        catOpt <- service.findById(id)
        res <- catOpt match {
          case Some(cat) => Ok(CategoryAdminView.editForm(cat))
          case None      => NotFound("Categoría no encontrada")
        }
      } yield res.withContentType(
        org.http4s.headers.`Content-Type`(org.http4s.MediaType.text.html)
      )

    case POST -> Root / "admin" / "category" / "delete" / IntVar(id) =>
      service.delete(id) *> SeeOther(
        Location(Uri.unsafeFromString("/admin/category"))
      )
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 