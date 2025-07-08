package http.controllers.admin

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.headers.Location
import org.http4s.headers.`Content-Type`
import service.CategoryService
import java.sql.Timestamp
import java.time.Instant
import model.Category
import _root_.http.views.admin.CategoryAdminView
import org.http4s.MediaType

class CategoryAdminController(categoryService: CategoryService) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    // Listar categorías (GET /admin/categories)
    case GET -> Root =>
      categoryService
        .findAll()
        .flatMap(categories =>
          Ok(CategoryAdminView.list(categories))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        )

    // Mostrar formulario de añadir (GET /admin/categories/add)
    case GET -> Root / "add" =>
      Ok(CategoryAdminView.addForm())
        .map(_.withContentType(`Content-Type`(MediaType.text.html)))

    // Mostrar formulario de edición (GET /admin/categories/edit/:id)
    case GET -> Root / "edit" / IntVar(id) =>
      categoryService.findById(id).flatMap {
        case Some(category) =>
          Ok(CategoryAdminView.editForm(category))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        case None =>
          NotFound("Category not found")
      }

    // Procesar añadir categoría (POST /admin/categories/add)
    case req @ POST -> Root / "add" =>
      req.as[UrlForm].flatMap { form =>
        val now = Timestamp.from(Instant.now())
        val category = Category(
          id = 0,
          name = form.getFirstOrElse("name", ""),
          slug = form.getFirstOrElse("slug", ""),
          description =
            Option(form.getFirstOrElse("description", "")).filter(_.nonEmpty),
          status = form.getFirstOrElse("status", "false").toBoolean,
          createdAt = now,
          updatedAt = now,
          deletedAt = None
        )
        categoryService.create(category) *>
          SeeOther(Location(uri"/admin/categories"))
      }
    case req @ POST -> Root / "edit" / IntVar(id) =>
      req.as[UrlForm].flatMap { form =>
        for {
          // Primero obtenemos la categoría existente para preservar createdAt
          existing <- categoryService.findById(id)
          now = Timestamp.from(Instant.now())

          updatedCategory = existing match {
            case Some(c) =>
              Category(
                id = id,
                name = form.getFirstOrElse("name", c.name),
                slug = form.getFirstOrElse("slug", c.slug),
                description = Option(form.getFirstOrElse("description", ""))
                  .filter(_.nonEmpty),
                status =
                  form.getFirstOrElse("status", c.status.toString).toBoolean,
                createdAt = c.createdAt, // Mantenemos la fecha original
                updatedAt = now,
                deletedAt = None
              )
            case None =>
              // Si no existe, creamos una nueva (aunque esto no debería ocurrir)
              Category(
                id = id,
                name = form.getFirstOrElse("name", ""),
                slug = form.getFirstOrElse("slug", ""),
                description = Option(form.getFirstOrElse("description", ""))
                  .filter(_.nonEmpty),
                status = form.getFirstOrElse("status", "false").toBoolean,
                createdAt = now,
                updatedAt = now,
                deletedAt = None
              )
          }

          _ <- categoryService.update(updatedCategory)
          response <- SeeOther(Location(uri"/admin/categories"))
        } yield response
      }
    // Eliminar categoría (POST /admin/categories/delete/:id)
    case POST -> Root / "delete" / IntVar(id) =>
      categoryService.delete(id) *>
        SeeOther(Location(uri"/admin/categories"))
  }
}
