file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
empty definition using fallback
non-local guesses:

offset: 606
uri: file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
text:
```scala
package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import http.views.CategoryAdminView
import service.CategoryService

class CategoryAdminController(service: CategoryService) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {

    case GET -> Root / "admin" / "category" =>
      for {
        cats <- service.findAll() // Necesitarías implementar esto
        res <- Ok(CategoryAdminView.list(cats))
      } yield res.withContentType(
        org.http4s.headers.`Content-Type`(org.http4s.MediaType.text.html)
      )

    case GET -> Root / "admin" / "categor@@y" / "add" =>
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