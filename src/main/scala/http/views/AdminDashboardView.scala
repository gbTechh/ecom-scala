package http.views

import scalatags.Text.all._
import scalatags.Text.tags2

object AdminDashboardView {
  def dashboard(): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Panel de Administración")
      ),
      body(
        h1("Panel de Administración"),
        ul(
          li(a(href := "/admin/category", "Gestionar Categorías")),
          li(a(href := "/", "Ir al sitio público"))
        )
      )
    ).render
  }
}
