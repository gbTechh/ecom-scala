package config

import scala.io.Source
import java.io.File

object EnvLoader {

  def loadEnv(): Unit = {
    val envFile = new File(".env")
    if (envFile.exists()) {
      val source = Source.fromFile(envFile)
      try {
        source.getLines().foreach { line =>
          val trimmed = line.trim
          if (trimmed.nonEmpty && !trimmed.startsWith("#")) {
            val parts = trimmed.split("=", 2)
            if (parts.length == 2) {
              val key = parts(0).trim
              val value = parts(1).trim
              System.setProperty(key, value)
              println(s"✅ Loaded env variable: $key")
            }
          }
        }
      } finally {
        source.close()
      }
      println("📁 .env file loaded successfully")
    } else {
      println("⚠️ .env file not found, using system environment variables")
    }
  }

  def getEnv(key: String): Option[String] = {
    Option(System.getProperty(key)).orElse(Option(System.getenv(key)))
  }

  def getEnvOrThrow(key: String): String = {
    getEnv(key).getOrElse(
      throw new RuntimeException(s"Environment variable $key not found")
    )
  }
}
