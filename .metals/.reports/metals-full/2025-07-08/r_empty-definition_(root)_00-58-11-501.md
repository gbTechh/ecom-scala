error id: file://<WORKSPACE>/src/main/scala/http/views/admin/CategoryAdminView.scala:scalatags/generic/Attrs#charset.
file://<WORKSPACE>/src/main/scala/http/views/admin/CategoryAdminView.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol scalatags/generic/Attrs#charset.
empty definition using fallback
non-local guesses:

offset: 425
uri: file://<WORKSPACE>/src/main/scala/http/views/admin/CategoryAdminView.scala
text:
```scala
package http.views.admin

import scalatags.Text.all._
import scalatags.Text.tags2
import model.Category

object CategoryAdminView {
  private def formValue(value: Option[String]): Frag =
    value.fold[Frag]("")(v => v)

  private def formTextarea(name: String, value: Option[String]) =
    textarea(name := name, formValue(value))
  def list(categories: List[Category]): String = {
    html(
      head(
        meta(charset@@ := "UTF-8"),
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

  def editForm(category: Category): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Editar Categoría")
      ),
      body(
        h1("Editar Categoría"),
        form(
          action := s"/admin/categories/edit/${category.id}", // Ruta corregida
          method := "post",
          p(
            "Nombre: ",
            input(name := "name", value := category.name, required := true)
          ),
          p(
            "Slug: ",
            input(name := "slug", value := category.slug, required := true)
          ),
          p(
            "Descripción: ",
            textarea(name := "description", category.description.getOrElse(""))
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

```


#### Short summary: 

empty definition using pc, found symbol in pc: 