package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import http.views.HomeView

object HomeController {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] { case GET -> Root =>
    Ok(HomeView.index())
      .map(
        _.withContentType(
          org.http4s.headers.`Content-Type`(MediaType.text.html)
        )
      )
  }
}
