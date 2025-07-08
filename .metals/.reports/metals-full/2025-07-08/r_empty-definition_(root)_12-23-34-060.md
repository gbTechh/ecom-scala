file://<WORKSPACE>/src/main/scala/http/controllers/SessionMiddleware.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
empty definition using fallback
non-local guesses:
	 -cartId.
	 -cartId#
	 -cartId().
	 -scala/Predef.cartId.
	 -scala/Predef.cartId#
	 -scala/Predef.cartId().
offset: 462
uri: file://<WORKSPACE>/src/main/scala/http/controllers/SessionMiddleware.scala
text:
```scala
package http.middleware

import cats.data.Kleisli
import cats.effect.IO
import org.http4s.{Request, Response}
import java.util.UUID

object SessionMiddleware {
  def apply(service: HttpRoutes[IO]): HttpRoutes[IO] = Kleisli {
    req: Request[IO] =>
      val maybeCartId = req.cookies.find(_.name == "cartId").map(_.content)

      val cartId = maybeCartId.getOrElse(UUID.randomUUID().toString)

      service(req).map(_.addCookie(ResponseCookie("cartId", cartId@@)))
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 