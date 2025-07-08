error id: file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala:service/CategoryService#findById().
file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol service/CategoryService#findById().
empty definition using fallback
non-local guesses:

offset: 762
uri: file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
text:
```scala
package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.Location
import service.CategoryService
import http.views.admin.CategoryAdminView
import java.sql.Timestamp
import java.time.Instant

class CategoryAdminController(categoryService: CategoryService) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root => 
      categoryService.findAll().flatMap(categories =>
        Ok(CategoryAdminView.list(categories))
          .map(_.withContentType(MediaType.text.html))
    
    case GET -> Root / "add" =>
      Ok(CategoryAdminView.addForm())
        .map(_.withContentType(MediaType.text.html))
    
    case GET -> Root / "edit" / IntVar(id) =>
      categoryService.findById@@(id).flatMap {
        case Some(category) => 
          Ok(CategoryAdminView.editForm(category))
            .map(_.withContentType(MediaType.text.html))
        case None => NotFound("Category not found")
      }
    
    case req @ POST -> Root / "add" =>
      req.as[UrlForm].flatMap { form =>
        val now = Timestamp.from(Instant.now())
        val category = model.Category(
          id = 0,
          name = form.getFirstOrElse("name", ""),
          slug = form.getFirstOrElse("slug", ""),
          description = Option(form.getFirstOrElse("description", "")).filter(_.nonEmpty),
          status = form.getFirstOrElse("status", "false").toBoolean,
          createdAt = now,
          updatedAt = now,
          deletedAt = None
        )
        categoryService.create(category) *> 
          SeeOther(Location(uri"/admin/categories"))
      }
    
    case POST -> Root / "delete" / IntVar(id) =>
      categoryService.delete(id) *> 
        SeeOther(Location(uri"/admin/categories"))
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 