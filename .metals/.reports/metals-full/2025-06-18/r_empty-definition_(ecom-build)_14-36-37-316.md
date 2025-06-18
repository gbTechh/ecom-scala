error id: file://<WORKSPACE>/build.sbt:
file://<WORKSPACE>/build.sbt
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 278
uri: file://<WORKSPACE>/build.sbt
text:
```scala
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "ecommerce",
    Compile / mainClass := Some("main.Main"),
    libraryDependencies ++= Seq(
       // HTTP4S para servidor web
      "o@@rg.http4s" %% "http4s-ember-server" % "0.23.24",
      "org.http4s" %% "http4s-ember-client" % "0.23.24",
      "org.http4s" %% "http4s-dsl" % "0.23.24",
      "org.http4s" %% "http4s-circe" % "0.23.24",
      
      // SSR - Plantillas HTML
      "com.typesafe.play" %% "twirl-api" % "1.6.2",
      "org.http4s" %% "http4s-twirl" % "0.23.24", // Integración http4s + twirl
      "com.lihaoyi" %% "scalatags" % "0.12.0", // Alternativa programática
      
      // Archivos estáticos
      "org.webjars" % "bootstrap" % "5.3.2",
      "org.webjars" % "jquery" % "3.7.1",
      "org.webjars.npm" % "htmx.org" % "1.9.6", // Para interactividad sin JS pesado
      
      // JSON
      "io.circe" %% "circe-generic" % "0.14.6",
      "io.circe" %% "circe-parser" % "0.14.6",
      
      // Cats Effect
      "org.typelevel" %% "cats-effect" % "3.5.2",
      
      // Base de datos - Doobie completo
      "org.tpolecat" %% "doobie-core" % "1.0.0-RC4",
      "org.tpolecat" %% "doobie-hikari" % "1.0.0-RC4",
      "org.tpolecat" %% "doobie-specs2" % "1.0.0-RC4",
      "org.tpolecat" %% "doobie-scalatest" % "1.0.0-RC4" % Test,
      "mysql" % "mysql-connector-java" % "8.0.33",
      
      // Configuración
      "com.github.pureconfig" %% "pureconfig" % "0.17.4",
      
      // Logging
      "ch.qos.logback" % "logback-classic" % "1.4.11",
      "org.typelevel" %% "log4cats-slf4j" % "2.6.0",
      
      // Session management para e-commerce
      "org.http4s" %% "http4s-server" % "0.23.24",
      
      // Testing
      "org.scalatest" %% "scalatest" % "3.2.17" % Test,
      "org.typelevel" %% "cats-effect-testing-scalatest" % "1.5.0" % Test
    )
  )

```


#### Short summary: 

empty definition using pc, found symbol in pc: 