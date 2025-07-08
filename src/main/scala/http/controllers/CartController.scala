// src/main/scala/http/controllers/CartController.scala
// Método helper para extraer cartId del header
package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers._
import model.Cart
import service.CartService
import org.typelevel.ci._
import http.views.CartView
import java.util.UUID
import org.http4s.util.CaseInsensitiveString
import scala.util.{Try, Success, Failure}

class CartController(cartService: CartService) {

  private def getCartIdFromRequest(req: Request[IO]): Option[String] = {
    req.cookies.find(_.name == "cartId").map(_.content)
  }

  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {

    // case GET -> Root / "view" / cartIdStr =>
    //   Try(UUID.fromString(cartIdStr)) match {
    //     case Success(cartId) =>
    //       cartService.getCart(cartId).flatMap {
    //         case Some(cart) =>
    //           Ok(CartView.view(cart, cart.id.toString))
    //             .map(_.withContentType(`Content-Type`(MediaType.text.html)))
    //         case None =>
    //           NotFound("Carrito no encontrado")
    //       }
    //     case Failure(_) =>
    //       BadRequest("ID de carrito inválido")
    //   }
    case GET -> Root / "view" / cartIdStr =>
      Try(UUID.fromString(cartIdStr)) match {
        case Success(cartId) =>
          cartService.getCartWithProducts(cartId).flatMap {
            case Some((cart, products)) =>
              Ok(CartView.viewWithProductNames(cart, products))
                .map(_.withContentType(`Content-Type`(MediaType.text.html)))
            case None =>
              NotFound("Carrito no encontrado")
          }
        case Failure(_) =>
          BadRequest("ID de carrito inválido")
      }

    case req @ POST -> Root / "add" =>
      println("🛒 POST /cart/add")
      println(s"Headers: ${req.headers}")

      req.as[UrlForm].flatMap { form =>
        val productId = form.values
          .get("productId")
          .flatMap(_.headOption)
          .map(_.toInt)
          .getOrElse(throw new IllegalArgumentException("Product ID requerido"))

        val quantity = form.values
          .get("quantity")
          .flatMap(_.headOption)
          .map(_.toInt)
          .getOrElse(1)

        getCartIdFromRequest(req) match {
          // Hay cookie cartId
          case Some(cartIdStr) =>
            Try(UUID.fromString(cartIdStr)) match {
              case Success(cartId) =>
                cartService.getCart(cartId).flatMap {
                  case Some(_) =>
                    cartService.addItem(cartId, productId, quantity).flatMap {
                      _ =>
                        SeeOther(
                          Location(Uri.unsafeFromString(s"/cart/view/$cartId"))
                        )
                    }
                  case None =>
                    for {
                      newCart <- cartService.createCart(None)
                      updatedCart <- cartService
                        .addItem(newCart.id, productId, quantity)
                    } yield {
                      Response[IO](Status.SeeOther)
                        .withHeaders(
                          Header.Raw(ci"Location", s"/cart/view/${newCart.id}")
                        )
                        .addCookie(
                          ResponseCookie("cartId", newCart.id.toString)
                        )
                    }
                }
              case Failure(_) =>
                BadRequest("ID de carrito inválido")
            }

          // No hay cookie cartId
          case None =>
            for {
              newCart <- cartService.createCart(None)
              updatedCart <- cartService
                .addItem(newCart.id, productId, quantity)
            } yield {
              Response[IO](Status.SeeOther)
                .withHeaders(
                  Header.Raw(ci"Location", s"/cart/view/${newCart.id}")
                )
                .addCookie(ResponseCookie("cartId", newCart.id.toString))
            }
        }
      }

    case POST -> Root / "remove" / cartIdStr / productIdStr =>
      (
        Try(UUID.fromString(cartIdStr)).toOption,
        productIdStr.toIntOption
      ) match {
        case (Some(cartId), Some(productId)) =>
          cartService.removeItem(cartId, productId).flatMap { _ =>
            SeeOther(
              Location(
                Uri(path = Uri.Path.unsafeFromString(s"/cart/view/$cartId"))
              )
            )
          }
        case _ =>
          BadRequest("Parámetros inválidos")
      }

    case GET -> Root / "checkout" / cartIdStr =>
      Try(UUID.fromString(cartIdStr)) match {
        case Success(cartId) =>
          cartService.getCart(cartId).flatMap {
            case Some(cart) =>
              Ok(CartView.checkout(cart))
                .map(_.withContentType(`Content-Type`(MediaType.text.html)))
            case None =>
              NotFound("Carrito no encontrado")
          }
        case Failure(_) =>
          BadRequest("ID de carrito inválido")
      }
  }
}
