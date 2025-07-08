file://<WORKSPACE>/src/main/scala/http/controllers/ProductAdminController.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
empty definition using fallback
non-local guesses:
	 -org/http4s/_root_/http/controllers/http.
	 -org/http4s/dsl/io/_root_/http/controllers/http.
	 -_root_/http/controllers/http.
	 -scala/Predef._root_.http.controllers.http.
offset: 329
uri: file://<WORKSPACE>/src/main/scala/http/controllers/ProductAdminController.scala
text:
```scala
package http.controllers

package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.Location
import service.{ProductService, CategoryService}
import http.views.admin.ProductAdminView
import java.sql.Timestamp
import java.time.Instant
import _root_.http.controllers.h@@ttp.views.http.views.admin.ProductAdminView

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
            .map(_.withContentType(MediaType.text.html))
        )

    case GET -> Root / "add" =>
      categoryService
        .findAll()
        .flatMap(categories =>
          Ok(ProductAdminView.addForm(categories))
            .map(_.withContentType(MediaType.text.html))
        )

    case GET -> Root / "edit" / IntVar(id) =>
      (for {
        product <- productService.findById(id)
        categories <- categoryService.findAll()
      } yield (product, categories)).flatMap {
        case (Some(product), categories) =>
          Ok(ProductAdminView.editForm(product, categories))
            .map(_.withContentType(MediaType.text.html))
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
          price = form.getFirstOrElse("price", "0").toDouble,
          categoryId = form.getFirstOrElse("categoryId", "0").toInt,
          stock = form.getFirstOrElse("stock", "0").toInt,
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