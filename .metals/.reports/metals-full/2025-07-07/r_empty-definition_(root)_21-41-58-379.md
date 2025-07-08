error id: file://<WORKSPACE>/src/main/scala/http/controllers/ProductAdminController.scala:
file://<WORKSPACE>/src/main/scala/http/controllers/ProductAdminController.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 201
uri: file://<WORKSPACE>/src/main/scala/http/controllers/ProductAdminController.scala
text:
```scala
package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.headers.Location
import org.http4s.headers.`Content-Type`
@@import service.{ProductService, CategoryService}
import java.sql.Timestamp
import java.time.Instant
import _root_.http.views.admin.ProductAdminView
import org.http4s.MediaType

class ProductAdminController(
    productService: ProductService,
    categoryService: CategoryService
) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root =>
      productService
        .findAll()
        .flatMap(products =>
          Ok(ProductAdminView.list(products))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        )

    case GET -> Root / "add" =>
      categoryService
        .findAll()
        .flatMap(categories =>
          Ok(ProductAdminView.addForm(categories))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        )

    case GET -> Root / "edit" / IntVar(id) =>
      (for {
        product <- productService.findById(id)
        categories <- categoryService.findAll()
      } yield (product, categories)).flatMap {
        case (Some(product), categories) =>
          Ok(ProductAdminView.editForm(product, categories))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        case _ => NotFound("Product not found")
      }

    case req @ POST -> Root / "add" =>
      req.as[UrlForm].flatMap { form =>
        val now = Timestamp.from(Instant.now())
        val product = model.Product(
          id = 0,
          name = form.getFirstOrElse("name", ""),
          slug = form.getFirstOrElse("slug", ""),
          description =
            Option(form.getFirstOrElse("description", "")).filter(_.nonEmpty),
          status = Option(form.getFirstOrElse("status", "false").toBoolean),
          stock = Option(form.getFirstOrElse("stock", "0").toInt),
          price = Option(form.getFirstOrElse("price", "0").toFloat),
          categoryId = Option(form.getFirstOrElse("categoryId", "0").toInt),
          createdAt = now,
          updatedAt = now,
          deletedAt = None
        )
        productService.create(product) *>
          SeeOther(Location(uri"/admin/products"))
      }

    case POST -> Root / "delete" / IntVar(id) =>
      productService.delete(id) *>
        SeeOther(Location(uri"/admin/products"))
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 