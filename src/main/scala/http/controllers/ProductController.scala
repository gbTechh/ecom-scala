package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.MediaType
import service.ProductService
import http.views.ProductView
import cats.syntax.option._

class ProductController(productService: ProductService) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root =>
      productService
        .findAll()
        .flatMap(products =>
          Ok(ProductView.list(products))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        )

    case GET -> Root / slug =>
      productService.findBySlug(slug).flatMap {
        case Some(product) =>
          Ok(ProductView.detail(product))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        case None =>
          NotFound("Product not found")
      }

    case GET -> Root / "category" / categorySlug =>
      productService.findByCategorySlug(categorySlug).flatMap { products =>
        Ok(ProductView.listByCategory(products, categorySlug))
          .map(_.withContentType(`Content-Type`(MediaType.text.html)))
      }
  }
}
