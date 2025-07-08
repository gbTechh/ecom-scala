package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.MediaType
import org.http4s.QueryParamDecoder
import service.ProductService
import http.views.ProductView

class ProductController(productService: ProductService) {
  // Definimos el decoder para el parámetro de búsqueda
  object OptionalSearchQueryParamMatcher
      extends OptionalQueryParamDecoderMatcher[String]("q")

  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    // Listar productos con opción de búsqueda (GET /products?q=term)
    case GET -> Root :? OptionalSearchQueryParamMatcher(searchTerm) =>
      searchTerm match {
        case Some(term) if term.nonEmpty => // term ahora es String
          productService.searchByName(term).flatMap { products =>
            Ok(ProductView.list(products, searchTerm))
              .map(_.withContentType(`Content-Type`(MediaType.text.html)))
          }
        case _ =>
          productService.findAll().flatMap { products =>
            Ok(ProductView.list(products))
              .map(_.withContentType(`Content-Type`(MediaType.text.html)))
          }
      }

    // Ver detalle de producto por slug (GET /products/:slug)
    case GET -> Root / slug =>
      productService.findBySlug(slug).flatMap {
        case Some(product) =>
          Ok(ProductView.detail(product))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        case None =>
          NotFound("Product not found")
      }

    // Listar productos por categoría (GET /products/category/:categorySlug)
    case GET -> Root / "category" / categorySlug =>
      productService.findByCategorySlug(categorySlug).flatMap { products =>
        Ok(ProductView.listByCategory(products, categorySlug))
          .map(_.withContentType(`Content-Type`(MediaType.text.html)))
      }
  }
}
