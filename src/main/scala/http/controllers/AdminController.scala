package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import http.views.AdminDashboardView

object AdminController {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] { case GET -> Root / "admin" =>
    Ok(AdminDashboardView.dashboard())
      .map(
        _.withContentType(
          org.http4s.headers.`Content-Type`(org.http4s.MediaType.text.html)
        )
      )
  }
}
