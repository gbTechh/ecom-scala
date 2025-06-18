package main

import cats.effect.{ExitCode, IO, IOApp}
import database.DatabaseConfig
import doobie.hikari.HikariTransactor // Importación adicional para Hikari
import doobie.implicits._ // Para usar sql interpolator
import http.server.Server

object Main extends Greeting with IOApp {

  def run(args: List[String]): IO[ExitCode] = {
    // val program = for {
    //   _ <- IO.println(greeting)
    //   _ <- IO.println("🔌 Testing database connection...")

    //   // Cargar configuración y usar el transactor dentro de un Resource
    //   config = DatabaseConfig.load()
    //   result <- DatabaseConfig.createTransactor(config).use { xa =>
    //     for {
    //       testResult <- DatabaseConfig.testConnection(xa)
    //       _ <- IO.println(testResult)
    //     } yield ()
    //   }

    //   _ <- IO.println("🔐 Database connection closed automatically by Resource")
    // } yield ExitCode.Success

    // program.handleErrorWith { error =>
    //   IO.println(s"❌ Error: ${error.getMessage}").as(ExitCode.Error)
    // }
    // Server.stream.as(ExitCode.Success)
    Server.stream.as(ExitCode.Success)
  }
}

trait Greeting {
  lazy val greeting: String =
    "🛒 Hola desde el ecommerce!"
}
