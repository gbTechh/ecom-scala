package http.controllers.admin

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.MediaType
import http.views.admin.AdminView

class AdminController(
    // Puedes inyectar servicios si necesitas mostrar datos en el dashboard
    // por ejemplo: val orderService: OrderService
) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root =>
      // Puedes agregar lógica para obtener estadísticas aquí
      // val stats = orderService.getDashboardStats().unsafeRunSync()
      Ok(AdminView.dashboard( /* stats */ ))
        .map(_.withContentType(`Content-Type`(MediaType.text.html)))

    // Puedes agregar más rutas del panel principal aquí si es necesario
    case GET -> Root / "settings" =>
      Ok(AdminView.settings())
        .map(_.withContentType(`Content-Type`(MediaType.text.html)))
  }
}
