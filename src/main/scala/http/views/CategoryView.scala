package http.views

import scalatags.Text.all._
import scalatags.Text.tags2
import model.Category

object CategoryView {
  def showCategory(category: Category): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Categoría: " + category.name)
      ),
      body(
        h1(s"Categoría: ${category.name}"),
        p(
          s"ID: ${category.id}"
        ), // Si id es Int. Si es Option[Int] usa .getOrElse("N/A")
        a(href := "/", "Volver al inicio")
      )
    ).render
  }

  def notFound(): String = {
    html(
      head(tags2.title("No encontrado")),
      body(h1("Categoría no encontrada"))
    ).render
  }
}
