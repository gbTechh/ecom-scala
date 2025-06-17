package main // Asegúrate de que el paquete coincida con la estructura

import cats.effect.{ExitCode, IO, IOApp}
import database.DatabaseConfig
import doobie.hikari.HikariTransactor // Importación adicional para Hikari

object DatabaseTest extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    val program = for {
      _ <- IO.println("🚀 Starting database connection test...")

      // Cargar configuración
      config = DatabaseConfig.load()
      _ <- IO.println(s"📊 Database URL: ${config.url}")
      _ <- IO.println(s"👤 Database User: ${config.user}")

      // Usar el transactor dentro de un Resource
      result <- DatabaseConfig.createTransactor(config).use { xa =>
        for {
          // Probar conexión
          testResult <- DatabaseConfig.testConnection(xa)
          _ <- IO.println(testResult)
        } yield ()
      }

      _ <- IO.println("🔐 Database connection closed automatically by Resource")
    } yield ExitCode.Success

    program.handleErrorWith { error =>
      IO.println(s"💥 Error: ${error.getMessage}").as(ExitCode.Error)
    }
  }
}
