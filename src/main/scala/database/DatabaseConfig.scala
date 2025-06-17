package database

import cats.effect.{IO, Resource}
import doobie._
import doobie.hikari.HikariTransactor
import doobie.implicits._
import config.EnvLoader
import cats.effect.unsafe.implicits.global // Para usar el ExecutionContext global (opcional)

case class DatabaseConfig(
    url: String,
    user: String,
    password: String,
    driver: String
)

object DatabaseConfig {
  def load(): DatabaseConfig = {
    EnvLoader.loadEnv()

    DatabaseConfig(
      url = EnvLoader.getEnvOrThrow("DATABASE_URL"),
      user = EnvLoader.getEnvOrThrow("DATABASE_USER"),
      password = EnvLoader.getEnvOrThrow("DATABASE_PASSWORD"),
      driver = EnvLoader
        .getEnv("DATABASE_DRIVER")
        .getOrElse("com.mysql.cj.jdbc.Driver")
    )
  }

  def createTransactor(
      config: DatabaseConfig
  ): Resource[IO, HikariTransactor[IO]] = {
    HikariTransactor.newHikariTransactor[IO](
      driverClassName = config.driver,
      url = config.url,
      user = config.user,
      pass = config.password,
      connectEC =
        cats.effect.unsafe.IORuntime.global.compute // Usa el ExecutionContext global
    )
  }

  def testConnection(xa: Transactor[IO]): IO[String] = {
    val query = sql"SELECT 1 as test".query[Int].unique

    query
      .transact(xa)
      .map { result =>
        s"🎉 Database connection successful! Test query returned: $result"
      }
      .handleErrorWith { error =>
        IO.pure(s"❌ Database connection failed: ${error.getMessage}")
      }
  }
}
