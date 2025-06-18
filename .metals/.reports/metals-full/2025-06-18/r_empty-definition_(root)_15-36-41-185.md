error id: file://<WORKSPACE>/src/main/scala/http/server/Server.scala:http/controllers/AdminController.routes.
file://<WORKSPACE>/src/main/scala/http/server/Server.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol http/controllers/AdminController.routes.
empty definition using fallback
non-local guesses:

offset: 929
uri: file://<WORKSPACE>/src/main/scala/http/server/Server.scala
text:
```scala
package http.server

import cats.effect._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.HttpRoutes
import org.http4s.dsl.io._

import com.comcast.ip4s._
import repository.CategoryRepository
import service.CategoryService
import http.controllers._

object Server {
  def stream: IO[Unit] = {
    for {
      config <- IO(database.DatabaseConfig.load())
      _ <- database.DatabaseConfig.createTransactor(config).use { xa =>
        val catRepo = CategoryRepository(xa)
        val catService = CategoryService(catRepo)
        val categoryAdminCtrl = new CategoryAdminController(catService)

        val routes: HttpRoutes[IO] = Router(
          "/" -> HttpRoutes.of[IO] { case GET -> Root =>
             Ok(HomeView.index()).map(_.withContentType(`Content-Type`(MediaType.text.html)))
          },
          "/admin" -> AdminController.routes@@,
          "/" -> categoryAdminCtrl.routes
        )

        EmberServerBuilder
          .default[IO]
          .withHost(host"localhost")
          .withPort(port"8080")
          .withHttpApp(routes.orNotFound)
          .build
          .use(_ => IO.never)
      }
    } yield ()
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 