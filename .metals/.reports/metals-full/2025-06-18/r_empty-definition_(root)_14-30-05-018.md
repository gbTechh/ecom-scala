error id: file://<WORKSPACE>/src/main/scala/main/Main.scala:cats/effect/IO.
file://<WORKSPACE>/src/main/scala/main/Main.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol cats/effect/IO.
empty definition using fallback
non-local guesses:

offset: 899
uri: file://<WORKSPACE>/src/main/scala/main/Main.scala
text:
```scala
package main

import cats.effect.{ExitCode, IO, IOApp}
import database.DatabaseConfig
import doobie.hikari.HikariTransactor // Importación adicional para Hikari
import doobie.implicits._ // Para usar sql interpolator

object Main extends Greeting with IOApp {

  def run(args: List[String]): IO[ExitCode] = {
    val program = for {
      _ <- IO.println(greeting)
      _ <- IO.println("🔌 Testing database connection...")

      // Cargar configuración y usar el transactor dentro de un Resource
      config = DatabaseConfig.load()
      result <- DatabaseConfig.createTransactor(config).use { xa =>
        for {
          testResult <- DatabaseConfig.testConnection(xa)
          _ <- IO.println(testResult)
        } yield ()
      }

      _ <- IO.println("🔐 Database connection closed automatically by Resource")
    } yield ExitCode.Success

    program.handleErrorWith { error =>
      IO@@.println(s"❌ Error: ${error.getMessage}").as(ExitCode.Error)
    }
    Server.stream.as(ExitCode.Success)
  }
}

trait Greeting {
  lazy val greeting: String =
    "🛒 Hello from Ecommerce App! - 10:42 PM -05 del 14/06/2025"
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 