error id: file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala:local14
file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -org/http4s/HttpRoutes#
	 -HttpRoutes#
	 -scala/Predef.HttpRoutes#
offset: 262
uri: file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala
text:
```scala
// src/main/scala/http/middleware/SessionMiddleware.scala

package http.middleware

import cats.data.Kleisli
import cats.effect.IO
import org.http4s._
import org.http4s.headers.`Set-Cookie`
import java.util.UUID

object SessionMiddleware {
  def apply(routes: Ht@@tpRoutes[IO]): HttpRoutes[IO] = Kleisli {
    req: Request[IO] =>
      val cartId = req.cookies
        .find(_.name == "cartId")
        .map(_.content)
        .getOrElse(UUID.randomUUID().toString)

      val updatedVault = req.attributes.withValue(Keys.CartIdKey, cartId)
      val updatedReq = req.withAttributes(updatedVault)

      routes(updatedReq).map { resp =>
        resp.addCookie(ResponseCookie(name = "cartId", content = cartId))
      }
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 