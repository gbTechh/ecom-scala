package http.views.admin

import scalatags.Text.all._
import scalatags.Text.tags2
import model.Category

object CategoryAdminView {

  def list(categories: List[Category]): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Lista de Categorías")
      ),
      body(
        h1("Categorías"),
        a(href := "/admin/category/add", "➕ Nueva categoría"),
        table(
          border := "1",
          // cellpa := "8",
          thead(
            tr(
              th("ID"),
              th("Nombre"),
              th("Slug"),
              th("Acciones")
            )
          ),
          tbody(
            categories.map { cat =>
              tr(
                td(cat.id.toString),
                td(cat.name),
                td(cat.slug),
                td(
                  a(
                    href := s"/admin/category/edit/${cat.id.toString()}",
                    "✏️ Editar"
                  ),
                  " | ",
                  form(
                    action := s"/admin/category/delete/${cat.id.toString()}",
                    method := "post",
                    input(`type` := "submit", value := "🗑️ Eliminar")
                  )
                )
              )
            }: _* // <- Esta es la clave
          )
        ),
        br(),
        a(href := "/admin", "⬅ Volver al panel")
      )
    ).render
  }

  def addForm(): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Agregar Categoría")
      ),
      body(
        h1("Nueva Categoría"),
        form(
          action := "/admin/category/add",
          method := "post",
          p("Nombre: ", input(name := "name")),
          p("Slug: ", input(name := "slug")),
          p("Descripción: ", textarea(name := "description")),
          p("Estado: ", input(name := "status")),
          p(input(`type` := "submit", value := "Guardar"))
        ),
        a(href := "/admin/category", "⬅ Volver a lista")
      )
    ).render
  }

  def editForm(category: Category): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Editar Categoría")
      ),
      body(
        h1("Editar Categoría"),
        form(
          action := s"/admin/category/edit/${category.id.toString()}",
          method := "post",
          p("Nombre: ", input(name := "name", value := category.name)),
          p("Slug: ", input(name := "slug", value := category.slug)),
          p(
            "Descripción: ",
            textarea(name := "description", category.description)
          ),
          p("Estado: ", input(name := "status", value := category.status)),
          p(input(`type` := "submit", value := "Actualizar"))
        ),
        a(href := "/admin/category", "⬅ Volver a lista")
      )
    ).render
  }
}
