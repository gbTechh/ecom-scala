package http.views.admin

import scalatags.Text.all._
import scalatags.Text.tags2
import model.Product
import model.Category

object ProductAdminView {

  def list(products: List[Product]): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Lista de Productos")
      ),
      body(
        h1("Productos"),
        a(href := "/admin/products/add", "➕ Nuevo producto"),
        table(
          border := "1",
          thead(
            tr(
              th("ID"),
              th("Nombre"),
              th("Precio"),
              th("Stock"),
              th("Acciones")
            )
          ),
          tbody(
            products.map { product =>
              tr(
                td(product.id.toString),
                td(product.name),
                td(
                  product.price.fold[Frag](span("N/A"))(p => span(p.toString))
                ),
                td(
                  product.stock.fold[Frag](span("N/A"))(s => span(s.toString))
                ),
                td(
                  a(
                    href := s"/admin/products/edit/${product.id}",
                    "✏️ Editar"
                  ),
                  " | ",
                  form(
                    action := s"/admin/products/delete/${product.id}",
                    method := "post",
                    input(`type` := "submit", value := "🗑️ Eliminar")
                  )
                )
              )
            }
          )
        ),
        br(),
        a(href := "/admin", "⬅ Volver al panel")
      )
    ).render
  }

  def addForm(categories: List[Category]): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Agregar Producto")
      ),
      body(
        h1("Nuevo Producto"),
        form(
          action := "/admin/products/add",
          method := "post",
          p("Nombre: ", input(name := "name", required := true)),
          p("Slug: ", input(name := "slug", required := true)),
          p("Descripción: ", textarea(name := "description")),
          p(
            "Precio: ",
            input(
              name := "price",
              `type` := "number",
              step := "0.01",
              min := "0",
              value := "0"
            )
          ),
          p(
            "Stock: ",
            input(name := "stock", `type` := "number", min := "0", value := "0")
          ),
          p(
            "Estado: ",
            select(
              name := "status",
              option(value := "true", "Activo"),
              option(value := "false", "Inactivo")
            )
          ),
          p(
            "Categoría: ",
            select(
              name := "categoryId",
              categories.map { cat =>
                option(value := cat.id.toString, cat.name)
              }
            )
          ),
          p(input(`type` := "submit", value := "Guardar"))
        ),
        a(href := "/admin/products", "⬅ Volver a lista")
      )
    ).render
  }

  def editForm(product: Product, categories: List[Category]): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Editar Producto")
      ),
      body(
        h1("Editar Producto"),
        form(
          action := s"/admin/products/edit/${product.id}",
          method := "post",
          p(
            "Nombre: ",
            input(name := "name", value := product.name, required := true)
          ),
          p(
            "Slug: ",
            input(name := "slug", value := product.slug, required := true)
          ),
          p(
            "Descripción: ",
            textarea(
              name := "description",
              product.description.getOrElse(""): String
            )
          ),
          p(
            "Precio: ",
            input(
              name := "price",
              `type` := "number",
              step := "0.01",
              min := "0",
              value := product.price.fold("0")(_.toString)
            )
          ),
          p(
            "Stock: ",
            input(
              name := "stock",
              `type` := "number",
              min := "0",
              value := product.stock.fold("0")(_.toString)
            )
          ),
          p(
            "Estado: ",
            select(
              name := "status",
              option(
                value := "true",
                "Activo",
                if (product.status.getOrElse(false)) selected := true else ()
              ),
              option(
                value := "false",
                "Inactivo",
                if (!product.status.getOrElse(true)) selected := true else ()
              )
            )
          ),
          p(
            "Categoría: ",
            select(
              name := "categoryId",
              categories.map { cat =>
                option(
                  value := cat.id.toString,
                  cat.name,
                  if (product.categoryId.contains(cat.id)) selected := true
                  else ()
                )
              }
            )
          ),
          p(input(`type` := "submit", value := "Actualizar"))
        ),
        a(href := "/admin/products", "⬅ Volver a lista")
      )
    ).render
  }
}
