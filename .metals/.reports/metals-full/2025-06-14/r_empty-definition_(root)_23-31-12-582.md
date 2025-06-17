error id: file://<WORKSPACE>/src/main/scala/database/DatabaseConfig.scala:
file://<WORKSPACE>/src/main/scala/database/DatabaseConfig.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1598
uri: file://<WORKSPACE>/src/main/scala/database/DatabaseConfig.scala
text:
```scala
package database

import cats.effect.{IO, Resource}
import io.getquill.doobie.DoobieContext
import io.getquill.{Literal, NamingStrategy}
import config.EnvLoader
import javax.sql.DataSource
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

case class DatabaseConfig(
    url: String,
    user: String,
    password: String,
    driver: String
)

object DatabaseConfig {
  // 1. Carga la configuración (igual que antes)
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

  // 2. Crea un DataSource HikariCP
  def createDataSource(config: DatabaseConfig): Resource[IO, DataSource] = {
    Resource.make(IO {
      val hikariConfig = new HikariConfig()
      hikariConfig.setJdbcUrl(config.url)
      hikariConfig.setUsername(config.user)
      hikariConfig.setPassword(config.password)
      hikariConfig.setDriverClassName(config.driver)

      new HikariDataSource(hikariConfig)
    })(ds => IO(ds.close()))
  }

  // 3. Crea el contexto de Quill (VERSIÓN CORREGIDA)
  def createQuillContext(
      dataSource: DataSource
  ): DoobieContext.MySQL[Literal] = {
    new DoobieContext.MySQL(Literal)
    // No pasamos el DataSource aquí, lo configuramos después
  }

  // 4. Test de conexión usando Quill
def testConnection(ctx: DoobieContext.MySQL[Literal]): IO[String] = {
  @@import ctx._
  
  // 1. Convertimos la operación de Quill a IO directamente
  val queryIO: IO[Int] = run(infix"SELECT 1".as[Int]).to[IO]  // Conversión explícita a IO

  // 2. Manejo de errores sobre IO
  queryIO.attempt.map {
    case Right(result) => s"🎉 Database connection successful! Test query returned: $result"
    case Left(error) => s"❌ Database connection failed: ${error.getMessage}"
  }
}
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 