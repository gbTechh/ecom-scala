package http.server

import cats.effect._
import org.http4s._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.dsl.io._
import com.comcast.ip4s._
import repository._
import service._
import http.controllers._
import org.http4s.MediaType
import database._
import http.controllers.admin.OrderAdminController
import http.controllers.admin.AdminController

object Server {
  def stream: IO[Unit] = {
    for {
      config <- IO(DatabaseConfig.load())
      _ <- DatabaseConfig.createTransactor(config).use { xa =>
        // Repositorios
        val catRepo = CategoryRepository(xa)
        val productRepo = ProductRepository(xa)
        val orderRepo = OrderRepository(xa)
        val orderItemRepo = OrderItemRepository(xa)

        // Servicios
        val catService = CategoryService(catRepo)
        val productService = ProductService(productRepo)
        val orderService = OrderService(orderRepo, orderItemRepo)

        // Controladores Admin
        val categoryAdminCtrl = new admin.CategoryAdminController(catService)
        val productAdminCtrl =
          new ProductAdminController(productService, catService)
        val orderAdminCtrl = new OrderAdminController(orderService)

        // Controladores Frontend
        val categoryCtrl = new CategoryController(catService)
        val productCtrl = new ProductController(productService)
        val homeCtrl = new HomeController()

        val adminCtrl = new AdminController()

        // Configuración de rutas
        val routes: HttpRoutes[IO] = Router(
          "/" -> homeCtrl.routes,
          "/categories" -> categoryCtrl.routes,
          "/products" -> productCtrl.routes,
          "/admin" -> Router(
            "" -> adminCtrl.routes, // Nueva ruta para el dashboard
            "/categories" -> categoryAdminCtrl.routes,
            "/products" -> productAdminCtrl.routes,
            "/orders" -> orderAdminCtrl.routes
          )
        )

        // Iniciar servidor
        EmberServerBuilder
          .default[IO]
          .withHost(host"localhost")
          .withPort(port"8080")
          .withHttpApp(routes.orNotFound)
          .build
          .use(_ => IO.never)
      }
    } yield ()
  }
}
