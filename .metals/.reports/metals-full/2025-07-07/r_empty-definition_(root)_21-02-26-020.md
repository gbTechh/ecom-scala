file://<WORKSPACE>/src/main/scala/http/views/admin/OrderAdminView.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
empty definition using fallback
non-local guesses:
	 -scalatags/Text.all.Timestamp.
	 -scalatags/Text.all.Timestamp#
	 -scalatags/Text.all.Timestamp().
	 -java/sql/Timestamp.
	 -java/sql/Timestamp#
	 -java/sql/Timestamp().
	 -Timestamp.
	 -Timestamp#
	 -Timestamp().
	 -scala/Predef.Timestamp.
	 -scala/Predef.Timestamp#
	 -scala/Predef.Timestamp().
offset: 177
uri: file://<WORKSPACE>/src/main/scala/http/views/admin/OrderAdminView.scala
text:
```scala
package http.views.admin

import scalatags.Text.all._
import scalatags.Text.tags2
import model.{Order, OrderItem, OrderStatus}
import java.time.format.
import java.sql.Timestamp@@

object OrderAdminView {
  private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

  def list(orders: List[Order]): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Admin - Orders"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(
          `class` := "container mt-4",
          h1("Orders"),
          table(
            `class` := "table table-striped",
            thead(
              tr(
                th("ID"),
                th("User ID"),
                th("Total"),
                th("Status"),
                th("Date"),
                th("Actions")
              )
            ),
            tbody(
              orders.map { order =>
                tr(
                  td(order.id),
                  td(order.userId),
                  td(f"$$${order.totalAmount}%.2f"),
                  td(statusBadge(order.status)),
                  td(order.createdAt.toLocalDateTime.format(dateFormatter)),
                  td(
                    a(
                      href := s"/admin/orders/${order.id}",
                      `class` := "btn btn-sm btn-primary",
                      "View"
                    ),
                    " ",
                    a(
                      href := s"/admin/orders/${order.id}/edit",
                      `class` := "btn btn-sm btn-secondary",
                      "Edit"
                    ),
                    " ",
                    form(
                      action := s"/admin/orders/${order.id}/cancel",
                      method := "post",
                      display := "inline",
                      button(
                        `type` := "submit",
                        `class` := "btn btn-sm btn-danger",
                        disabled := (order.status == OrderStatus.Cancelled),
                        "Cancel"
                      )
                    )
                  )
                )
              }
            )
          )
        )
      )
    ).render
  }

  def detail(order: Order, items: List[OrderItem]): String = {
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
            div(`class` := "card-header", "Order Details"),
            div(
              `class` := "card-body",
              dl(
                `class` := "row",
                dt(`class` := "col-sm-3", "User ID"),
                dd(`class` := "col-sm-9", order.userId),
                dt(`class` := "col-sm-3", "Status"),
                dd(`class` := "col-sm-9", statusBadge(order.status)),
                dt(`class` := "col-sm-3", "Total Amount"),
                dd(`class` := "col-sm-9", f"$$${order.totalAmount}%.2f"),
                dt(`class` := "col-sm-3", "Created At"),
                dd(
                  `class` := "col-sm-9",
                  order.createdAt.toLocalDateTime.format(dateFormatter)
                ),
                dt(`class` := "col-sm-3", "Updated At"),
                dd(
                  `class` := "col-sm-9",
                  order.updatedAt.toLocalDateTime.format(dateFormatter)
                )
              )
            )
          ),
          div(
            `class` := "card",
            div(`class` := "card-header", "Order Items"),
            div(
              `class` := "card-body",
              table(
                `class` := "table",
                thead(
                  tr(
                    th("Product ID"),
                    th("Quantity"),
                    th("Unit Price"),
                    th("Subtotal")
                  )
                ),
                tbody(
                  items.map { item =>
                    tr(
                      td(item.productId),
                      td(item.quantity),
                      td(f"$$${item.unitPrice}%.2f"),
                      td(f"$$${item.unitPrice * item.quantity}%.2f")
                    )
                  },
                  tr(
                    td(colspan := 3, strong("Total")),
                    td(strong(f"$$${order.totalAmount}%.2f"))
                  )
                )
              )
            )
          ),
          div(
            `class` := "mt-3",
            a(
              href := "/admin/orders",
              `class` := "btn btn-secondary",
              "Back to Orders"
            )
          )
        )
      )
    ).render
  }

  def editForm(order: Order): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title(s"Edit Order #${order.id}"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(
          `class` := "container mt-4",
          h1(s"Edit Order #${order.id}"),
          form(
            action := s"/admin/orders/${order.id}/update-status",
            method := "post",
            div(
              `class` := "mb-3",
              label(`class` := "form-label", "Status"),
              select(
                name := "status",
                `class` := "form-select",
                OrderStatus.allStatuses.map { status =>
                  option(
                    value := status.toString,
                    if (order.status == status) selected := true,
                    status.toString
                  )
                }
              )
            ),
            button(
              `type` := "submit",
              `class` := "btn btn-primary",
              "Update Status"
            ),
            " ",
            a(
              href := s"/admin/orders/${order.id}",
              `class` := "btn btn-secondary",
              "Cancel"
            )
          )
        )
      )
    ).render
  }

  private def statusBadge(status: OrderStatus): Frag = {
    val (color, text) = status match {
      case OrderStatus.Pending    => ("warning", "Pending")
      case OrderStatus.Processing => ("info", "Processing")
      case OrderStatus.Shipped    => ("primary", "Shipped")
      case OrderStatus.Delivered  => ("success", "Delivered")
      case OrderStatus.Cancelled  => ("danger", "Cancelled")
    }
    span(`class` := s"badge bg-$color", text)
  }

  private implicit class RichTimestamp(t: Timestamp) {
    def toLocalDateTime = t.toLocalDateTime
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 