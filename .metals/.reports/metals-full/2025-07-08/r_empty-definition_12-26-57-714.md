error id: file://<WORKSPACE>/src/main/scala/http/controllers/SessionMiddleware.scala:java/util/UUID#
file://<WORKSPACE>/src/main/scala/http/controllers/SessionMiddleware.scala
empty definition using pc, found symbol in pc: java/util/UUID#
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -UUID.
	 -UUID#
	 -UUID().
	 -scala/Predef.UUID.
	 -scala/Predef.UUID#
	 -scala/Predef.UUID().
package http.middleware

import cats.data.Kleisli
import cats.effect.IO
import org.http4s.{Request, Response}
import java.util.UUID
import org.http4s.HttpRoutes
import org.http4s.ResponseCookie

object SessionMiddleware {
  def apply(service: HttpRoutes[IO]): HttpRoutes[IO] = Kleisli {
    req: Request[IO] =>
      val maybeCartId = req.cookies.find(_.name == "cartId").map(_.content)

      val cartId = maybeCartId.getOrElse(UUID.randomUUID().toString)

      service(req).map(_.addCookie(ResponseCookie("cartId", cartId)))
  }
}


#### Short summary: 

empty definition using pc, found symbol in pc: java/util/UUID#