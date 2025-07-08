error id: file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala:`<error>`#`<error>`.
file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -org/http4s/req/cookies.
	 -req/cookies.
	 -scala/Predef.req.cookies.
offset: 491
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
      val cartId = req.coo@@kies
        .find(_.name == "cartId")
        .map(_.content)
        .getOrElse(UUID.randomUUID().toString)

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

empty definition using pc, found symbol in pc: 