package http.middleware

import cats.data.Kleisli
import cats.effect.IO
import org.http4s._
import org.http4s.headers.`Set-Cookie`
import org.typelevel.ci._
import java.util.UUID

object SessionMiddleware {
  def apply(routes: HttpRoutes[IO]): HttpRoutes[IO] = Kleisli { req =>
    val cartId = req.cookies
      .find(_.name == "cartId")
      .map(_.content)
      .getOrElse(UUID.randomUUID().toString)

    val updatedReq =
      req.putHeaders(Header.Raw(ci"X-Cart-Id", cartId)) // <- corregido

    routes(updatedReq).map { resp =>
      resp.addCookie(ResponseCookie(name = "cartId", content = cartId))
    }
  }
}
