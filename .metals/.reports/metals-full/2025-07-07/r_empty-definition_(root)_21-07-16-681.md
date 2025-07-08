error id: file://<WORKSPACE>/src/main/scala/http/views/HomeView.scala:
file://<WORKSPACE>/src/main/scala/http/views/HomeView.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 2463
uri: file://<WORKSPACE>/src/main/scala/http/views/HomeView.scala
text:
```scala
package http.views

import scalatags.Text.all._
import scalatags.Text.tags2

object HomeView {
  def index(): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Welcome to Our Store"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(`class` := "container",
          h1("Welcome to Our Store"),
          nav(
            ul(`class` := "nav",
              li(`class` := "nav-item",
                a(`class` := "nav-link", href := "/products", "Browse Products")
              ),
              li(`class` := "nav-item",
                a(`class` := "nav-link", href := "/categories", "Categories")
              ),
              li(`class` := "nav-item",
                a(`class` := "nav-link", href := "/about", "About Us")
              ),
              li(`class` := "nav-item",
                a(`class` := "nav-link", href := "/contact", "Contact")
              )
            )
          ),
          div(`class` := "mt-4",
            h2("Featured Products"),
            // Aquí podrías incluir productos destacados
            a(href := "/products", "View all products")
          )
        )
      )
    ).render
  }

  def about(): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("About Us"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(`class` := "container",
          h1("About Our Store"),
          p("We are the best online store in the world!"),
          a(href := "/", "Back to Home")
        )
      )
    ).render
  }

  def contact(): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Contact Us"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        )
      ),
      body(
        div(`class` := "container",
          h1("Contact Us"),
          form(
            div(`class` := "mb-3",
              label(`class` := "form-label", "Name"),
              input(`type` := "text", `class` := "form-control", name := "name")
            ),
            div(`class` := "mb-3",
              label(`class` := "form-label", "Email"),
              input(`type` := "ema@@il", `class` := "form-control", name := "email")
            ),
            div(`class` := "mb-3",
              label(`class` := "form-label", "Message"),
              textarea(`class` := "form-control", name := "message", rows := 5)
            ),
            button(`type` := "submit", `class` := "btn btn-primary", "Send Message")
          ),
          a(href := "/", "Back to Home")
        )
      )
    ).render
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 