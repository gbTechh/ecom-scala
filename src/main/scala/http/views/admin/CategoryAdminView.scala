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
        a(href := "/admin/categories/add", "➕ Nueva categoría"),
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
                    href := s"/admin/categories/edit/${cat.id.toString()}",
                    "✏️ Editar"
                  ),
                  " | ",
                  form(
                    action := s"/admin/categories/delete/${cat.id.toString()}",
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
          action := "/admin/categories/add",
          method := "post",
          p("Nombre: ", input(name := "name")),
          p("Slug: ", input(name := "slug")),
          p("Descripción: ", textarea(name := "description")),
          p("Estado: ", input(name := "status")),
          p(input(`type` := "submit", value := "Guardar"))
        ),
        a(href := "/admin/categories", "⬅ Volver a lista")
      )
    ).render
  }

  private def renderTextareaContent(content: Option[String]): Frag =
    content.map(str => str: Frag).getOrElse("": Frag)

  def editForm(category: Category): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Editar Categoría")
      ),
      body(
        h1("Editar Categoría"),
        // Asegúrate que el action coincide con la ruta del controlador
        form(
          action := s"/admin/categories/edit/${category.id}",
          method := "post",
          p("Nombre: ", input(name := "name", value := category.name)),
          p("Slug: ", input(name := "slug", value := category.slug)),
          p(
            "Descripción: ",
            textarea(
              name := "description",
              renderTextareaContent(category.description)
            )
          ),
          p(
            "Estado: ",
            select(
              name := "status",
              option(
                value := "true",
                if (category.status) selected := true,
                "Activo"
              ),
              option(
                value := "false",
                if (!category.status) selected := true,
                "Inactivo"
              )
            )
          ),
          p(input(`type` := "submit", value := "Actualizar"))
        ),
        a(href := "/admin/categories", "⬅ Volver a lista")
      )
    ).render
  }

}
