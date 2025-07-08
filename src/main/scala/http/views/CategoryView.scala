package http.views

import scalatags.Text.all._
import scalatags.Text.tags2
import model.Category

object CategoryView {
  // Función auxiliar para manejar descripciones opcionales
  private def descriptionDisplay(desc: Option[String]): Frag =
    desc.fold[Frag](span("No description available"))(d => span(d))

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
        div(
          `class` := "container",
          h1("Product Categories"),
          div(
            `class` := "list-group",
            categories.map { category =>
              a(
                href := s"/categories/${category.slug}",
                `class` := "list-group-item list-group-item-action",
                category.name
              )
            }
          )
        )
      )
    ).render
  }

  def show(category: Category): String = {
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
        div(
          `class` := "container",
          h1(category.name),
          p(
            descriptionDisplay(category.description)
          ), // Usamos la función auxiliar aquí
          a(
            href := s"/products/category/${category.slug}",
            `class` := "btn btn-primary",
            "View Products"
          ),
          a(
            href := "/categories",
            `class` := "btn btn-link",
            "Back to Categories"
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
        div(
          `class` := "container",
          h1("Category Not Found"),
          p("The requested category could not be found."),
          a(href := "/categories", "Back to Categories")
        )
      )
    ).render
  }
}
