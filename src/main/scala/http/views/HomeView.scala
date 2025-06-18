package http.views

import scalatags.Text.all._
import scalatags.Text.tags2

object HomeView {
  def index(): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Inicio")
      ),
      body(
        h1("Bienvenido al Ecommerce SSR en Scala"),
        p("Esta es la página pública.")
      )
    ).render
  }
}
