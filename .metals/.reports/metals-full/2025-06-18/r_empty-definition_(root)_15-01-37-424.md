file://<WORKSPACE>/src/main/scala/http/server/Server.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
empty definition using fallback
non-local guesses:

offset: 852
uri: file://<WORKSPACE>/src/main/scala/http/server/Server.scala
text:
```scala
package http.server

import cats.effect._
import org.http4s.HttpRoutes
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import service._
import repository._
import http.controllers.CategoryController
import doobie.util.transactor.Transactor
import database.DatabaseConfig
import com.comcast.ip4s._

object Server {
  def stream: IO[Unit] = {
    for {
      config <- IO(DatabaseConfig.load())
      _ <- DatabaseConfig.createTransactor(config).use { xa =>
        val categoryRepo = CategoryRepository(xa)
        val categoryService = CategoryService(categoryRepo)
        val categoryController = new CategoryController(categoryService)

        val allRoutes: HttpRoutes[IO] = categoryController.routes

        EmberServerBuilder
          .default[IO]
          .withHost(host"localhost")
          .withPort(port"8@@080")
          .withHttpApp(allRoutes.orNotFound)
          .build
          .use(_ => IO.never)
      }
    } yield ()
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 