package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.MediaType
import http.views.HomeView

class HomeController {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root =>
      Ok(HomeView.index())
        .map(_.withContentType(`Content-Type`(MediaType.text.html)))

    case GET -> Root / "about" =>
      Ok(HomeView.about())
        .map(_.withContentType(`Content-Type`(MediaType.text.html)))

    case GET -> Root / "contact" =>
      Ok(HomeView.contact())
        .map(_.withContentType(`Content-Type`(MediaType.text.html)))
  }
}
