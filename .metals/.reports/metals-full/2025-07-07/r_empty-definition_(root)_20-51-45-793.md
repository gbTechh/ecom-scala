file://<WORKSPACE>/src/main/scala/http/controllers/OrderAdminController.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
empty definition using fallback
non-local guesses:

offset: 1529
uri: file://<WORKSPACE>/src/main/scala/http/controllers/OrderAdminController.scala
text:
```scala
package http.controllers.admin

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.Location
import org.http4s.MediaType
import org.http4s.headers.`Content-Type`
import service.OrderService
import model.{Order, OrderStatus}
import java.sql.Timestamp
import java.time.Instant
import _root_.http.views.admin.OrderAdminView
import org.http4s.implicits._

class OrderAdminController(orderService: OrderService) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root =>
      orderService
        .findAll()
        .flatMap(orders =>
          Ok(OrderAdminView.list(orders))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        )

    case GET -> Root / IntVar(orderId) =>
      orderService.getOrderWithItems(orderId).flatMap {
        case Some((order, items)) =>
          Ok(OrderAdminView.detail(order, items))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        case None =>
          NotFound("Order not found")
      }

    case GET -> Root / IntVar(orderId) / "edit" =>
      orderService.findById(orderId).flatMap {
        case Some(order) =>
          Ok(OrderAdminView.editForm(order))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        case None =>
          NotFound("Order not found")
      }

    case req @ POST -> Root / IntVar(orderId) / "update-status" =>
      req.as[UrlForm].flatMap { form =>
        OrderStatus.fromString(form.getFirstOrElse("status", "")) @@match {
          case Some(status) =>
            orderService.updateOrderStatus(orderId, status) *>
              SeeOther(Location(uri"/admin/orders" / orderId.toString))
          case None =>
            BadRequest("Invalid status")
        }
      }

    case POST -> Root / IntVar(orderId) / "cancel" =>
      orderService.cancelOrder(orderId) *>
        SeeOther(Location(uri"/admin/orders" / orderId.toString))
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 