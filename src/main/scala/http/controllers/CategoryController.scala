package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.MediaType
import service.{CategoryService, ProductService}
import http.views.CategoryView

class CategoryController(
    categoryService: CategoryService,
    productService: ProductService
) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root =>
      categoryService
        .findAll()
        .flatMap(categories =>
          Ok(CategoryView.list(categories))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        )

    case GET -> Root / slug =>
      for {
        maybeCategory <- categoryService.findBySlug(slug)
        products <- productService.findByCategorySlug(slug)
        response <- maybeCategory match {
          case Some(category) =>
            Ok(CategoryView.show(category, products))
              .map(_.withContentType(`Content-Type`(MediaType.text.html)))
          case None =>
            NotFound(CategoryView.notFound())
              .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        }
      } yield response
  }

}
