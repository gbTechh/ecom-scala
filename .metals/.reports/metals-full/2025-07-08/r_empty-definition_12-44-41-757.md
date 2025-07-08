error id: file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala:local0
file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -org/http4s/HttpRoutes#
	 -HttpRoutes#
	 -scala/Predef.HttpRoutes#
offset: 275
uri: file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala
text:
```scala
// src/main/scala/http/middleware/SessionMiddleware.scala

package http.middleware

import cats.data.Kleisli
import org.http4s._
import org.http4s.headers.`Set-Cookie`
import java.util.UUID

object SessionMiddleware {
  private val CartIdKey = "cartId"

  def apply(routes: H@@ttpRoutes[IO]): HttpRoutes[IO] = Kleisli { req =>
    val cartId = req.cookies
      .find(_.name == CartIdKey)
      .map(_.content)
      .getOrElse(UUID.randomUUID().toString)

    val updatedReq = req.mapK(k => k).withAttributes(
      req.attributes.put(AttributeMap.Entry(AttributeMap.Key.get[String](CartIdKey), cartId))
    )

    routes(updatedReq).map(_.addCookie(ResponseCookie(CartIdKey, cartId)))
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 