ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "ecommerce",
    Compile / mainClass := Some("main.Main"),
    libraryDependencies ++= Seq(
      // HTTP4S para servidor web
      "org.http4s" %% "http4s-ember-server" % "0.23.24",
      "org.http4s" %% "http4s-ember-client" % "0.23.24",
      "org.http4s" %% "http4s-dsl" % "0.23.24",
      "org.http4s" %% "http4s-circe" % "0.23.24",

      // JSON
      "io.circe" %% "circe-generic" % "0.14.6",
      "io.circe" %% "circe-parser" % "0.14.6",

      // Cats Effect
      "org.typelevel" %% "cats-effect" % "3.5.2",

      // Base de datos - Doobie completo
      "org.tpolecat" %% "doobie-core" % "1.0.0-RC4",
      "org.tpolecat" %% "doobie-hikari" % "1.0.0-RC4",
      "org.tpolecat" %% "doobie-specs2" % "1.0.0-RC4",
      // CRÍTICO: Esta dependencia permite derivación automática
      "org.tpolecat" %% "doobie-scalatest" % "1.0.0-RC4" % Test,
      "mysql" % "mysql-connector-java" % "8.0.33",

      // Configuración
      "com.github.pureconfig" %% "pureconfig" % "0.17.4",

      // Testing
      "org.scalatest" %% "scalatest" % "3.2.17" % Test,
      "org.typelevel" %% "cats-effect-testing-scalatest" % "1.5.0" % Test
    )
  )
