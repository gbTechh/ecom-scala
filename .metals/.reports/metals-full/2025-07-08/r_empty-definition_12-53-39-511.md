error id: file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala:java/util/UUID#
file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala
empty definition using pc, found symbol in pc: java/util/UUID#
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -org/http4s/UUID.
	 -org/http4s/UUID#
	 -org/http4s/UUID().
	 -java/util/UUID.
	 -java/util/UUID#
	 -java/util/UUID().
	 -UUID.
	 -UUID#
	 -UUID().
	 -scala/Predef.UUID.
	 -scala/Predef.UUID#
	 -scala/Predef.UUID().
offset: 207
uri: file://<WORKSPACE>/src/main/scala/http/middleware/SessionMiddleware.scala
text:
```scala
// src/main/scala/http/middleware/SessionMiddleware.scala

package http.middleware

import cats.data.Kleisli
import cats.effect.IO
import org.http4s._
import org.http4s.headers.`Set-Cookie`
import java.util.@@UUID

import org.typelevel.vault.Vault

object Keys {
  val CartIdKey: Vault.Key[String] = Vault.Key[String]("cartId")
}
object SessionMiddleware {
  def apply(routes: HttpRoutes[IO]): HttpRoutes[IO] = Kleisli { req =>
    val cartId = req.cookies
      .find(_.name == "cartId")
      .map(_.content)
      .getOrElse(UUID.randomUUID().toString)

    // Insertamos el cartId en los atributos del request
    val updatedVault = req.attributes.insert(Keys.CartIdKey, cartId)
    val updatedReq = req.withAttributes(updatedVault)

    routes(updatedReq).map { resp =>
      resp.addCookie(ResponseCookie(name = "cartId", content = cartId))
    }
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/util/UUID#