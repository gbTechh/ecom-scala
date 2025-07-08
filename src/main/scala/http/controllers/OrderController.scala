package http.controllers

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.MediaType
import service.{OrderService, CartService, ProductService, UserService}
import http.views.OrderView
import model.{Order, OrderStatus, Cart, CartItem, OrderItem, User, UserRole}
import java.time.Instant
import java.sql.Timestamp
import java.util.UUID
import cats.implicits._

class OrderController(
    orderService: OrderService,
    cartService: CartService,
    productService: ProductService,
    userService: UserService // ✅ Inyectado
) {

  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {

    case req @ POST -> Root / "create" / cartId =>
      (for {
        form <- req.as[UrlForm]
        cart <- cartService.getCart(UUID.fromString(cartId))
        response <- cart match {
          case Some(c) =>
            val emailOpt = form.getFirst("email")
            emailOpt match {
              case Some(email) =>
                for {
                  maybeUser <- userService.findByEmail(email)
                  user <- maybeUser match {
                    case Some(u) => IO.pure(u)
                    case None =>
                      val now = Timestamp.from(Instant.now())
                      val newUser = User(
                        id = 0,
                        email = email,
                        password = "guest",
                        role = UserRole.Customer,
                        firstName = None,
                        lastName = None,
                        status = true,
                        createdAt = now,
                        updatedAt = now,
                        deletedAt = None
                      )
                      for {
                        _ <- userService.createUser(newUser)
                        createdUser <- userService.findByEmail(email)
                        user <- createdUser match {
                          case Some(u) => IO.pure(u)
                          case None =>
                            IO.raiseError(
                              new Exception("Error al crear el usuario")
                            )
                        }
                      } yield user
                  }
                  val order = createOrderFromCart(c, form, user.id)
                  createdOrderId <- orderService.createOrderAndGetId(order)
                  val orderItems = c.items.map(item =>
                    convertToOrderItem(item, createdOrderId)
                  )
                  _ <- orderItems.traverse_(orderService.createOrderItem _)
                  _ <- cartService.clearCart(c.id)
                  response <- Ok(
                    OrderView.confirmation(order.copy(id = createdOrderId))
                  ).map(_.withContentType(`Content-Type`(MediaType.text.html)))
                } yield response
              case None =>
                BadRequest("Correo electrónico requerido")
            }

          case None =>
            NotFound("Cart not found")
        }
      } yield response).handleErrorWith { err =>
        IO.println(
          s"❌ ERROR INTERNO AL CREAR ORDEN:\n${err.getMessage}\n${err.getStackTrace.mkString("\n")}"
        ) *>
          InternalServerError("Error interno al procesar la orden.")
      }

    case GET -> Root / IntVar(orderId) =>
      for {
        orderWithItems <- orderService.getOrderWithItems(orderId)
        response <- orderWithItems match {
          case Some((order, items)) =>
            val products =
              items.traverse(item => productService.findById(item.productId))
            products.flatMap { products =>
              val itemsWithProducts = items.zip(products.flatten)
              Ok(OrderView.detail(order, itemsWithProducts))
                .map(_.withContentType(`Content-Type`(MediaType.text.html)))
            }
          case None =>
            NotFound("Order not found")
        }
      } yield response

    case GET -> Root / "user" / userId =>
      for {
        orders <- orderService.findByUserId(userId.toInt)
        response <- Ok(OrderView.list(orders))
          .map(_.withContentType(`Content-Type`(MediaType.text.html)))
      } yield response
  }

  private def createOrderFromCart(
      cart: Cart,
      form: UrlForm,
      userId: Int
  ): Order = {
    Order(
      id = 0,
      userId = userId,
      totalAmount = cart.items.map(i => i.unitPrice * i.quantity).sum,
      status = OrderStatus.Pending,
      createdAt = Timestamp.from(Instant.now()),
      updatedAt = Timestamp.from(Instant.now()),
      deletedAt = None
    )
  }

  private def convertToOrderItem(item: CartItem, orderId: Int): OrderItem = {
    OrderItem(
      id = 0,
      orderId = orderId,
      productId = item.productId,
      quantity = item.quantity,
      unitPrice = item.unitPrice,
      createdAt = Timestamp.from(Instant.now()),
      updatedAt = Timestamp.from(Instant.now())
    )
  }
}
