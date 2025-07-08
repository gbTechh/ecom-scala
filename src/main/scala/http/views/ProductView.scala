package http.views

import scalatags.Text.all._
import scalatags.Text.tags2
import model.Product
import java.text.NumberFormat
import java.util.Locale

object ProductView {
  private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

  private def priceDisplay(price: Option[Float]): Frag =
    price.fold[Frag](span("Price not available"))(p =>
      span(currencyFormat.format(p))
    )

  private def descriptionDisplay(description: Option[String]): Frag =
    description.fold[Frag](span("No description available"))(d => span(d))

  def list(products: List[Product]): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("All Products"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(
          `class` := "container",
          h1("All Products"),
          div(
            `class` := "row",
            products.map { product =>
              div(
                `class` := "col-md-4 mb-4",
                div(
                  `class` := "card",
                  div(
                    `class` := "card-body",
                    h5(`class` := "card-title", product.name),
                    p(`class` := "card-text", priceDisplay(product.price)),
                    a(
                      href := s"/products/${product.slug}",
                      `class` := "btn btn-primary",
                      "View Details"
                    )
                  )
                )
              )
            }
          )
        )
      )
    ).render
  }

  def detail(product: Product): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title(product.name),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(
          `class` := "container mt-4",
          div(
            `class` := "row",
            div(
              `class` := "col-md-6",
              img(
                src := "https://via.placeholder.com/400",
                `class` := "img-fluid"
              )
            ),
            div(
              `class` := "col-md-6",
              h1(product.name),
              h3(priceDisplay(product.price)),
              p(descriptionDisplay(product.description)),
              form(
                div(
                  `class` := "mb-3",
                  label(`class` := "form-label", "Quantity"),
                  input(
                    `type` := "number",
                    `class` := "form-control",
                    value := "1",
                    min := "1",
                    max := product.stock.getOrElse(10).toString,
                    name := "quantity"
                  )
                ),
                button(
                  `type` := "submit",
                  `class` := "btn btn-primary btn-lg",
                  "Add to Cart"
                )
              ),
              a(
                href := "/products",
                `class` := "btn btn-link",
                "Back to Products"
              )
            )
          )
        )
      )
    ).render
  }

  def listByCategory(products: List[Product], categorySlug: String): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title(s"Products in $categorySlug"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(
          `class` := "container",
          h1(s"Products in category: $categorySlug"),
          div(
            `class` := "row",
            products.map { product =>
              div(
                `class` := "col-md-4 mb-4",
                div(
                  `class` := "card",
                  div(
                    `class` := "card-body",
                    h5(`class` := "card-title", product.name),
                    p(`class` := "card-text", priceDisplay(product.price)),
                    a(
                      href := s"/products/${product.slug}",
                      `class` := "btn btn-primary",
                      "View Details"
                    )
                  )
                )
              )
            }
          ),
          a(href := "/products", `class` := "btn btn-link", "View All Products")
        )
      )
    ).render
  }
}
