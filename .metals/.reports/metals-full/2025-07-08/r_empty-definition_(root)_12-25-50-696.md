error id: file://<WORKSPACE>/src/main/scala/http/controllers/CartController.scala:java/util/UUID#
file://<WORKSPACE>/src/main/scala/http/controllers/CartController.scala
empty definition using pc, found symbol in pc: java/util/UUID#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 523
uri: file://<WORKSPACE>/src/main/scala/http/controllers/CartController.scala
text:
```scala
package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.MediaType
import org.http4s.headers.Location
import service.CartService
import http.views.CartView
import java.util.UUID

class CartController(cartService: CartService) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "view" / cartId =>
      for {
        cart <- cartService.getCart(UUID.fromString(cartId))
        response <- cart match {@@
          case Some(c) =>
            Ok(CartView.view(c))
              .map(_.withContentType(`Content-Type`(MediaType.text.html)))
          case None =>
            NotFound("Cart not found")
        }
      } yield response

    case req @ POST -> Root / "add" =>
      for {
        form <- req.as[UrlForm]
        productId = form.values
          .get("productId")
          .flatMap(_.headOption)
          .map(_.toInt)
          .getOrElse(
            throw new IllegalArgumentException("Product ID is required")
          )
        quantity = form.values
          .get("quantity")
          .flatMap(_.headOption)
          .map(_.toInt)
          .getOrElse(1)
        // El middleware ya nos da el carrito en la solicitud
        cart <- req.attributes
          .lookup(sessionManager.manager.key)
          .liftTo[IO](
            new IllegalStateException("Cart not found in session")
          )
        updatedCart <- cartService.addItem(cart.id, productId, quantity)
        response <- SeeOther(
          Location(Uri.unsafeFromString(s"/products/${productId}"))
        )
      } yield response

    case POST -> Root / "remove" / cartId / productId =>
      for {
        cart <- cartService.removeItem(UUID.fromString(cartId), productId.toInt)
        response <- SeeOther(
          Location(Uri.unsafeFromString(s"/cart/view/$cartId"))
        )
      } yield response

    case GET -> Root / "checkout" / cartId =>
      for {
        cart <- cartService.getCart(UUID.fromString(cartId))
        response <- cart match {
          case Some(c) =>
            Ok(CartView.checkout(c))
              .map(_.withContentType(`Content-Type`(MediaType.text.html)))
          case None =>
            NotFound("Cart not found")
        }
      } yield response
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/util/UUID#