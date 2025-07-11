package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.Location
import org.http4s.syntax.literals._
import http.views.CategoryAdminView
import service.CategoryService
import java.sql.Timestamp
import java.time.Instant
import model.Category

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
      Ok(CategoryAdminView.addForm()).map(
        _.withContentType(
          org.http4s.headers.`Content-Type`(MediaType.text.html)
        )
      )

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
    case req @ POST -> Root / "admin" / "category" / "add" =>
      for {
        form <- req.as[UrlForm]
        name = form.getFirstOrElse("name", "").trim
        slug = form.getFirstOrElse("slug", "").trim
        description = Option(form.getFirstOrElse("description", "").trim)
          .filter(_.nonEmpty)
        statusStr = form.getFirstOrElse("status", "false").trim.toLowerCase
        status = statusStr == "true" || statusStr == "on" || statusStr == "1"

        now = Timestamp.from(Instant.now())
        fakeId = 0 // No se usa, el DB genera el ID

        category = Category(
          id = fakeId,
          name = name,
          slug = slug,
          description = description,
          status = status,
          createdAt = now,
          updatedAt = now,
          deletedAt = None
        )

        _ <- service.create(category)
        res <- SeeOther(Location(uri"/admin/category"))
      } yield res

  }
}
