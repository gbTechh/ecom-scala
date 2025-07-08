error id: file://<WORKSPACE>/src/main/scala/http/views/CategoryView.scala:
file://<WORKSPACE>/src/main/scala/http/views/CategoryView.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1841
uri: file://<WORKSPACE>/src/main/scala/http/views/CategoryView.scala
text:
```scala
package http.views

import scalatags.Text.all._
import scalatags.Text.tags2
import model.Category

object CategoryView {
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
        div(`class` := "container",
          h1("Product Categories"),
          div(`class` := "list-group",
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
        div(`class` := "container",
          h1(category.name),
          p(category.description.getOrElse("No description available")),
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
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/@@css/bootstrap.min.css"
        )
      ),
      body(
        div(`class` := "container",
          h1("Category Not Found"),
          p("The requested category could not be found."),
          a(href := "/categories", "Back to Categories")
        )
      )
    ).render
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 