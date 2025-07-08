error id: file://<WORKSPACE>/src/main/scala/http/views/CartView.scala:
file://<WORKSPACE>/src/main/scala/http/views/CartView.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 49
uri: file://<WORKSPACE>/src/main/scala/http/views/CartView.scala
text:
```scala
package http.views

import scalatags.Text.all._
i@@mport scalatags.Text.tags2
import model.Cart
import java.text.NumberFormat
import java.util.Locale

object CartView {
  private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

  private def priceDisplay(price: Float): Frag = span(
    currencyFormat.format(price)
  )

  // def view(cart: Cart): String = {
  //   val total = cart.items.map(i => i.unitPrice * i.quantity).sum

  //   html(
  //     head(
  //       meta(charset := "UTF-8"),
  //       tags2.title("Your Shopping Cart"),
  //       link(
  //         rel := "stylesheet",
  //         href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
  //       )
  //     ),
  //     body(
  //       div(
  //         `class` := "container mt-4",
  //         h1("Your Shopping Cart"),
  //         if (cart.items.isEmpty) {
  //           div(
  //             `class` := "alert alert-info",
  //             "Your cart is empty"
  //           )
  //         } else {
  //           div(
  //             table(
  //               `class` := "table",
  //               thead(
  //                 tr(
  //                   th("Product"),
  //                   th("Price"),
  //                   th("Quantity"),
  //                   th("Subtotal"),
  //                   th("Action")
  //                 )
  //               ),
  //               tbody(
  //                 cart.items.map { item =>
  //                   tr(
  //                     td(
  //                       s"Product ${item.productId}"
  //                     ), // Aquí deberías mostrar el nombre real
  //                     td(priceDisplay(item.unitPrice)),
  //                     td(item.quantity.toString),
  //                     td(priceDisplay(item.unitPrice * item.quantity)),
  //                     td(
  //                       form(
  //                         action := s"/cart/remove/${cart.id}/${item.productId}",
  //                         method := "post",
  //                         button(
  //                           `type` := "submit",
  //                           `class` := "btn btn-danger btn-sm",
  //                           "Remove"
  //                         )
  //                       )
  //                     )
  //                   )
  //                 }
  //               )
  //             ),
  //             div(
  //               `class` := "text-end mb-4",
  //               h4("Total: ", priceDisplay(total))
  //             ),
  //             div(
  //               `class` := "text-end",
  //               a(
  //                 href := s"/cart/checkout/${cart.id}",
  //                 `class` := "btn btn-primary btn-lg",
  //                 "Proceed to Checkout"
  //               )
  //             )
  //           )
  //         },
  //         a(
  //           href := "/products",
  //           `class` := "btn btn-link",
  //           "Continue Shopping"
  //         )
  //       )
  //     )
  //   ).render
  // }
  def view(cart: Cart, cartId: String): String = {
    val total = cart.items.map(i => i.unitPrice * i.quantity).sum

    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Your Shopping Cart"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap @5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(
          `class` := "container mt-4",
          h1("Your Shopping Cart"),
          if (cart.items.isEmpty) {
            div(`class` := "alert alert-info", "Your cart is empty")
          } else {
            div(
              table(
                `class` := "table",
                thead(
                  tr(
                    th("Product"),
                    th("Price"),
                    th("Quantity"),
                    th("Subtotal"),
                    th("Action")
                  )
                ),
                tbody(
                  cart.items.map { item =>
                    tr(
                      td(
                        s"Product ID: ${item.productId}"
                      ), // Aquí deberías mostrar el nombre real
                      td(priceDisplay(item.unitPrice)),
                      td(item.quantity.toString),
                      td(priceDisplay(item.unitPrice * item.quantity)),
                      td(
                        form(
                          action := s"/cart/remove/$cartId/${item.productId}",
                          method := "post",
                          button(
                            `type` := "submit",
                            `class` := "btn btn-danger btn-sm",
                            "Remove"
                          )
                        )
                      )
                    )
                  }
                )
              ),
              div(
                `class` := "text-end mb-4",
                h4("Total: ", priceDisplay(total))
              ),
              div(
                `class` := "text-end",
                a(
                  href := s"/cart/checkout/$cartId",
                  `class` := "btn btn-primary btn-lg",
                  "Proceed to Checkout"
                )
              )
            )
          },
          a(href := "/products", `class` := "btn btn-link", "Continue Shopping")
        )
      )
    ).render
  }
  def checkout(cart: Cart): String = {
    val total = cart.items.map(i => i.unitPrice * i.quantity).sum

    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Checkout"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(
          `class` := "container mt-4",
          h1("Checkout"),
          form(
            action := s"/orders/create/${cart.id}",
            method := "post",
            div(
              `class` := "mb-3",
              h3("Shipping Information"),
              div(
                `class` := "row g-3",
                div(
                  `class` := "col-md-6",
                  label(`class` := "form-label", "First Name"),
                  input(
                    `type` := "text",
                    `class` := "form-control",
                    name := "firstName",
                    required := true
                  )
                ),
                div(
                  `class` := "col-md-6",
                  label(`class` := "form-label", "Last Name"),
                  input(
                    `type` := "text",
                    `class` := "form-control",
                    name := "lastName",
                    required := true
                  )
                ),
                div(
                  `class` := "col-12",
                  label(`class` := "form-label", "Address"),
                  input(
                    `type` := "text",
                    `class` := "form-control",
                    name := "address",
                    required := true
                  )
                ),
                div(
                  `class` := "col-md-6",
                  label(`class` := "form-label", "City"),
                  input(
                    `type` := "text",
                    `class` := "form-control",
                    name := "city",
                    required := true
                  )
                ),
                div(
                  `class` := "col-md-4",
                  label(`class` := "form-label", "State"),
                  input(
                    `type` := "text",
                    `class` := "form-control",
                    name := "state",
                    required := true
                  )
                ),
                div(
                  `class` := "col-md-2",
                  label(`class` := "form-label", "Zip"),
                  input(
                    `type` := "text",
                    `class` := "form-control",
                    name := "zip",
                    required := true
                  )
                )
              )
            ),
            div(
              `class` := "mb-3",
              h3("Payment Information"),
              // Aquí irían los campos de pago en un caso real
              div(
                `class` := "alert alert-info",
                "Payment information would be collected here in a real application"
              )
            ),
            div(
              `class` := "mb-3",
              h3("Order Summary"),
              table(
                `class` := "table",
                tbody(
                  cart.items.map { item =>
                    tr(
                      td(s"Product ${item.productId}"),
                      td(priceDisplay(item.unitPrice)),
                      td(item.quantity.toString),
                      td(priceDisplay(item.unitPrice * item.quantity))
                    )
                  },
                  tr(
                    th("Total"),
                    th(
                      colspan := "3",
                      `class` := "text-end",
                      priceDisplay(total)
                    )
                  )
                )
              )
            ),
            button(
              `type` := "submit",
              `class` := "btn btn-primary btn-lg",
              "Place Order"
            )
          )
        )
      )
    ).render
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 