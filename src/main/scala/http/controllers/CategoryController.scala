package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.MediaType
import http.views.CategoryView
import service.CategoryService

class CategoryController(service: CategoryService) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "category" / IntVar(id) =>
      for {
        result <- service.findById(id)
        response <- result match {
          case Some(cat) =>
            Ok(CategoryView.showCategory(cat))
              .map(_.withContentType(`Content-Type`(MediaType.text.html)))
          case None =>
            NotFound(CategoryView.notFound())
              .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        }
      } yield response
  }
}
