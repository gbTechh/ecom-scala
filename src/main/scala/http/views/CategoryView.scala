package http.views

import scalatags.Text.all._
import scalatags.Text.tags2
import model.{Category, Product}
import java.text.NumberFormat
import java.util.Locale

object CategoryView {
  private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

  private def priceDisplay(price: Option[Float]): Frag =
    price.fold[Frag](span("Price not available"))(p =>
      span(currencyFormat.format(p))
    )

  private def descriptionDisplay(desc: Option[String]): Frag =
    desc.fold[Frag](span("No description available"))(d => span(d))

  private def navigationMenu(active: String = ""): Frag =
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
            `class` := s"nav-link ${if (active == "products") "active" else ""}",
            href := "/products",
            "All Products"
          ),
          a(
            `class` := s"nav-link ${if (active == "categories") "active" else ""}",
            href := "/categories",
            "Categories"
          )
        )
      )
    )

  def list(categories: List[Category]): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Categories"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        navigationMenu("categories"),
        div(
          `class` := "container",
          h1("Product Categories"),
          if (categories.isEmpty) {
            div(
              `class` := "alert alert-warning",
              "No categories available"
            )
          } else {
            div(
              `class` := "row row-cols-1 row-cols-md-3 g-4",
              categories.map { category =>
                div(
                  `class` := "col",
                  div(
                    `class` := "card h-100",
                    div(
                      `class` := "card-body",
                      h5(`class` := "card-title", category.name),
                      p(
                        `class` := "card-text",
                        descriptionDisplay(category.description)
                      ),
                      a(
                        href := s"/categories/${category.slug}",
                        `class` := "btn btn-primary",
                        "View Products"
                      )
                    )
                  )
                )
              }
            )
          }
        )
      )
    ).render
  }

  def show(category: Category, products: List[Product]): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title(category.name),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        navigationMenu("categories"),
        div(
          `class` := "container",
          h1(category.name),
          p(descriptionDisplay(category.description)),

          // Lista de productos
          h2("Productos en esta categoria"),
          if (products.isEmpty) {
            div(
              `class` := "alert alert-info",
              "NO hay prodcutos para esta categoría"
            )
          } else {
            div(
              `class` := "row row-cols-1 row-cols-md-3 g-4",
              products.map { product =>
                div(
                  `class` := "col",
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
                        `class` := "btn btn-outline-primary mt-2",
                        "Ver detalles"
                      )
                    )
                  )
                )
              }
            )
          },
          a(
            href := "/categories",
            `class` := "btn btn-link mt-3",
            "Regresar a categorias"
          )
        )
      )
    ).render
  }

  def notFound(): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Category Not Found"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        navigationMenu(),
        div(
          `class` := "container",
          h1("Categoria no encontrada"),
          div(
            `class` := "alert alert-danger",
            "The requested category could not be found."
          ),
          a(
            href := "/categories",
            `class` := "btn btn-primary",
            "Regresar a Categorias"
          )
        )
      )
    ).render
  }
}
