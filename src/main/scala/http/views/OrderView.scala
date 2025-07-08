package http.views

import scalatags.Text.all._
import scalatags.Text.tags2
import model.{Order, OrderItem, Product}
import java.text.NumberFormat
import java.util.Locale
import model.Order
import model.OrderItem

object OrderView {
  private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

  private def toFrag(value: Any): Frag = value.toString

  def confirmation(order: Order): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Order Confirmation"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(
          `class` := "container mt-4",
          div(
            `class` := "card",
            div(
              `class` := "card-body",
              h1(`class` := "card-title", "Thank you for your order!"),
              h4(`class` := "card-subtitle mb-3", s"Order #${order.id}"),
              p(
                `class` := "card-text",
                "Your order has been received and is being processed."
              ),
              p(
                `class` := "card-text",
                s"Total: ${currencyFormat.format(order.totalAmount)}"
              ),
              p(`class` := "card-text", s"Status: ${order.status}"),
              a(
                href := "/products",
                `class` := "btn btn-primary",
                "Continue Shopping"
              )
            )
          )
        )
      )
    ).render
  }
  def list(orders: List[Order]): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Your Orders"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(
          `class` := "container mt-4",
          h1("Your Orders"),
          if (orders.isEmpty) {
            div(
              `class` := "alert alert-info",
              "You haven't placed any orders yet"
            )
          } else {
            div(
              `class` := "list-group",
              orders.map { order =>
                div(
                  `class` := "list-group-item",
                  h4(`class` := "mb-3", s"Order #${order.id}"),
                  div(
                    `class` := "d-flex justify-content-between",
                    span(s"Total: ${currencyFormat.format(order.totalAmount)}"),
                    span(s"Status: ${order.status}")
                  ),
                  div(
                    `class` := "mt-2",
                    a(
                      href := s"/orders/${order.id}",
                      `class` := "btn btn-sm btn-outline-primary",
                      "View Details"
                    )
                  )
                )
              }
            )
          },
          a(
            href := "/products",
            `class` := "btn btn-link mt-3",
            "Continue Shopping"
          )
        )
      )
    ).render
  }

  def detail(order: Order, items: List[(OrderItem, Product)]): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title(s"Order #${order.id}"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(
          `class` := "container mt-4",
          h1(s"Order #${order.id}"),
          div(
            `class` := "card mb-4",
            div(
              `class` := "card-body",
              h4(`class` := "card-title", "Order Summary"),
              p(`class` := "card-text", s"Status: ${order.status}"),
              p(
                `class` := "card-text",
                s"Total: ${currencyFormat.format(order.totalAmount)}"
              )
            )
          ),
          h3("Order Items"),
          table(
            `class` := "table",
            thead(
              tr(
                th("Product"),
                th("Price"),
                th("Quantity"),
                th("Subtotal")
              )
            ),
            tbody(
              items.map { case (item, product) =>
                tr(
                  td(toFrag(product.name)),
                  td(toFrag(currencyFormat.format(item.unitPrice))),
                  td(toFrag(item.quantity)),
                  td(
                    toFrag(
                      currencyFormat.format(item.unitPrice * item.quantity)
                    )
                  )
                )
              }
            )
          ),
          a(
            href := s"/orders/user/${order.userId}",
            `class` := "btn btn-link",
            "Back to Orders"
          )
        )
      )
    ).render
  }
}
