error id: file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala:java/util/UUID#randomUUID().
file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala
empty definition using pc, found symbol in pc: java/util/UUID#randomUUID().
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -org/http4s/UUID.randomUUID.
	 -org/http4s/UUID.randomUUID#
	 -org/http4s/UUID.randomUUID().
	 -java/util/UUID.randomUUID.
	 -java/util/UUID.randomUUID#
	 -java/util/UUID.randomUUID().
	 -UUID.randomUUID.
	 -UUID.randomUUID#
	 -UUID.randomUUID().
	 -scala/Predef.UUID.randomUUID.
	 -scala/Predef.UUID.randomUUID#
	 -scala/Predef.UUID.randomUUID().
offset: 588
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
import org.http4s.AttributeKey

object SessionMiddleware {
  private val CartIdKey = AttributeKey[String]("cartId")
  val CartIdKey = AttributeKey[String]("cartId")
  def apply(routes: HttpRoutes[IO]): HttpRoutes[IO] = Kleisli {
    req: Request[IO] =>
      val cartId = req.cookies
        .find(_.name == "cartId")
        .map(_.content)
        .getOrElse(UUID.randomUUID@@().toString)

      val updatedReq = req.withAttribute(CartIdKey, cartId)

      routes(updatedReq).map { resp =>
        resp.addCookie(
          ResponseCookie(name = "cartId", content = cartId)
        )
      }
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/util/UUID#randomUUID().