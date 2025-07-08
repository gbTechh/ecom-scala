error id: file://<WORKSPACE>/src/main/scala/http/views/ProductView.scala:scala/Any#toString().
file://<WORKSPACE>/src/main/scala/http/views/ProductView.scala
empty definition using pc, found symbol in pc: scala/Any#toString().
semanticdb not found
empty definition using fallback
non-local guesses:
	 -toString.
	 -toString#
	 -toString().
	 -scala/Predef.toString.
	 -scala/Predef.toString#
	 -scala/Predef.toString().
offset: 2428
uri: file://<WORKSPACE>/src/main/scala/http/views/ProductView.scala
text:
```scala
package http.views

import scalatags.Text.all._
import scalatags.Text.tags2
import model.Product
import java.text.NumberFormat
import java.util.Locale

object ProductView {
  private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

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
        div(`class` := "container",
          h1("All Products"),
          div(`class` := "row",
            products.map { product =>
              div(`class` := "col-md-4 mb-4",
                div(`class` := "card",
                  div(`class` := "card-body",
                    h5(`class` := "card-title", product.name),
                    p(`class` := "card-text",
                      product.price.map(p => currencyFormat.format(p)).getOrElse("Price not available")
                    ),
                    a(href := s"/products/${product.slug}", `class` := "btn btn-primary", "View Details")
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
        div(`class` := "container mt-4",
          div(`class` := "row",
            div(`class` := "col-md-6",
              // Aquí iría la imagen del producto
              img(src := "https://via.placeholder.com/400", `class` := "img-fluid")
            ),
            div(`class` := "col-md-6",
              h1(product.name),
              h3(product.price.map(currencyFormat.format).getOrElse("")),
              p(product.description.getOrElse("No description available")),
              form(
                div(`class` := "mb-3",
                  label(`class` := "form-label", "Quantity"),
                  input(
                    `type` := "number",
                    `class` := "form-control",
                    value := "1",
                    min := "1",
                    max := product.stock.getOrElse(10).toStrin@@g,
                    name := "quantity"
                  )
                ),
                button(`type` := "submit", `class` := "btn btn-primary btn-lg", "Add to Cart")
              ),
              a(href := "/products", `class` := "btn btn-link", "Back to Products")
            )
          )
        )
      )
    ).render
  }

  def listByCategory(products: L
```


#### Short summary: 

empty definition using pc, found symbol in pc: scala/Any#toString().