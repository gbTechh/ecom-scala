package http.views

import scalatags.Text.all._
import scalatags.Text.tags2
import model.Product
import java.text.NumberFormat
import java.util.Locale
import java.util.UUID

object ProductView {
  private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

  private def priceDisplay(price: Option[Float]): Frag =
    price.fold[Frag](span("Price not available"))(p =>
      span(currencyFormat.format(p))
    )

  private def descriptionDisplay(description: Option[String]): Frag =
    description.fold[Frag](span("No description available"))(d => span(d))

  def list(
      products: List[Product],
      searchTerm: Option[String] = None
  ): String = {
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
        // Barra de navegación
        div(
          `class` := "navbar navbar-expand-lg navbar-light bg-light mb-4",
          div(
            `class` := "container",
            a(
              `class` := "navbar-brand",
              href := "/",
              "Home"
            ),
            div(
              `class` := "navbar-nav",
              a(
                `class` := "nav-link",
                href := "/products",
                "All Products"
              )
            )
          )
        ),
        div(
          `class` := "container",
          h1("All Products"),

          // Barra de búsqueda
          div(
            `class` := "row mb-4",
            div(
              `class` := "col-md-6",
              form(
                action := "/products",
                method := "get",
                div(
                  `class` := "input-group",
                  input(
                    `type` := "text",
                    `class` := "form-control",
                    name := "q",
                    placeholder := "Search products...",
                    value := searchTerm.getOrElse("")
                  ),
                  div(
                    `class` := "input-group-append",
                    button(
                      `type` := "submit",
                      `class` := "btn btn-outline-secondary",
                      "Search"
                    )
                  )
                )
              )
            ),
            div(
              `class` := "col-md-6 text-right",
              a(
                href := "/products",
                `class` := "btn btn-link",
                "View All Products"
              )
            )
          ),

          // Mensaje de resultados de búsqueda
          searchTerm.filter(_.nonEmpty).map { term =>
            div(`class` := "alert alert-info", s"Showing results for: '$term'")
          },

          // Listado de productos
          div(
            `class` := "row",
            if (products.isEmpty) {
              div(
                `class` := "col-12",
                div(`class` := "alert alert-warning", "No products found")
              )
            } else {
              products.map { product =>
                div(
                  `class` := "col-md-4 mb-4",
                  div(
                    `class` := "card h-100",
                    div(
                      `class` := "card-body",
                      h5(`class` := "card-title", product.name),
                      p(`class` := "card-text", priceDisplay(product.price)),
                      p(
                        `class` := "card-text text-muted small",
                        descriptionDisplay(product.description)
                      ),
                      a(
                        href := s"/products/${product.slug}",
                        `class` := "btn btn-primary mt-2",
                        "View Details"
                      )
                    )
                  )
                )
              }
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
                src := "https://via.placeholder.com/400x300",
                `class` := "img-fluid",
                alt := product.name
              )
            ),
            div(
              `class` := "col-md-6",
              h1(product.name),
              h3(priceDisplay(product.price)),
              hr,
              h4("Descripcion"),
              p(descriptionDisplay(product.description)),
              hr,
              div(
                product.stock match {
                  case Some(stock) if stock > 0 =>
                    form(
                      action := "/cart/add",
                      method := "post",
                      input(
                        `type` := "hidden",
                        name := "productId",
                        value := product.id.toString
                      ),
                      div(
                        `class` := "input-group mb-3",
                        input(
                          `type` := "number",
                          `class` := "form-control",
                          name := "quantity",
                          value := "1",
                          min := "1",
                          max := stock.toString
                        ),
                        button(
                          `type` := "submit",
                          `class` := "btn btn-primary btn-lg",
                          "Agregar al carrito"
                        )
                      )
                    )
                  case _ =>
                    button(
                      `class` := "btn btn-secondary btn-lg disabled",
                      "Out of Stock"
                    )
                }
              ),
              a(
                href := "/products",
                `class` := "btn btn-link mt-2",
                "Regresar a productos"
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
        tags2.title(s"Products in category: $categorySlug"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(
          `class` := "container",
          h1(s"Products in category: $categorySlug"),

          // Enlace para volver a todos los productos
          div(
            `class` := "mb-4",
            a(
              href := "/products",
              `class` := "btn btn-link",
              "Back to All Products"
            )
          ),

          // Listado de productos
          div(
            `class` := "row",
            if (products.isEmpty) {
              div(
                `class` := "col-12",
                div(
                  `class` := "alert alert-warning",
                  "No products found in this category"
                )
              )
            } else {
              products.map { product =>
                div(
                  `class` := "col-md-4 mb-4",
                  div(
                    `class` := "card h-100",
                    div(
                      `class` := "card-body",
                      h5(`class` := "card-title", product.name),
                      p(`class` := "card-text", priceDisplay(product.price)),
                      p(
                        `class` := "card-text text-muted small",
                        descriptionDisplay(product.description)
                      ),
                      a(
                        href := s"/products/${product.slug}",
                        `class` := "btn btn-primary mt-2",
                        "View Details"
                      )
                    )
                  )
                )
              }
            }
          )
        )
      )
    ).render
  }
  // ... (mantén tus otros métodos detail y listByCategory igual) ...
}
