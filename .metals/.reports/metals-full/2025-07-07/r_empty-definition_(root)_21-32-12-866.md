error id: file://<WORKSPACE>/src/main/scala/http/controllers/AdminController.scala:
file://<WORKSPACE>/src/main/scala/http/controllers/AdminController.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 2454
uri: file://<WORKSPACE>/src/main/scala/http/controllers/AdminController.scala
text:
```scala
// src/main/scala/http/views/admin/AdminView.scala
package http.views.admin

import scalatags.Text.all._
import scalatags.Text.tags2

object AdminView {
  def dashboard(): String = {
    html(
      head(
        meta(charset := "UTF-8"),
        tags2.title("Admin Dashboard"),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        ),
        link(
          rel := "stylesheet",
          href := "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css"
        )
      ),
      body(
        div(
          `class` := "d-flex",
          // Sidebar
          div(
            `class` := "bg-dark text-white",
            style := "width: 250px; min-height: 100vh",
            div(
              `class` := "p-4",
              h3(`class` := "text-center mb-4", "Admin Panel"),
              ul(
                `class` := "nav flex-column",
                li(
                  `class` := "nav-item",
                  a(
                    href := "/admin",
                    `class` := "nav-link text-white",
                    i(`class` := "bi bi-speedometer2 me-2"),
                    "Dashboard"
                  )
                ),
                li(
                  `class` := "nav-item",
                  a(
                    href := "/admin/products",
                    `class` := "nav-link text-white",
                    i(`class` := "bi bi-box-seam me-2"),
                    "Products"
                  )
                ),
                li(
                  `class` := "nav-item",
                  a(
                    href := "/admin/categories",
                    `class` := "nav-link text-white",
                    i(`class` := "bi bi-tags me-2"),
                    "Categories"
                  )
                ),
                li(
                  `class` := "nav-item",
                  a(
                    href := "/admin/orders",
                    `class` := "nav-link text-white",
                    i(`class` := "bi bi-receipt me-2"),
                    "Orders"
                  )
                )
              )
            )
          ),

          // Main content
          div(
            `class` := "flex-grow-1 p-4",
            h1("Admin Dashboard"),
            div(
              `class` := "row",
              div(
                `class` := "col-md-4 @@mb-4",
                div(
                  `class` := "card bg-primary text-white",
                  div(
                    `class` := "card-body",
                    h5(`class` := "card-title", "Products"),
                    p(`class` := "card-text", "Manage your products"),
                    a(
                      href := "/admin/products",
                      `class` := "btn btn-light",
                      "Go to Products"
                    )
                  )
                )
              ),
              div(
                `class` := "col-md-4 mb-4",
                div(
                  `class` := "card bg-success text-white",
                  div(
                    `class` := "card-body",
                    h5(`class` := "card-title", "Categories"),
                    p(`class` := "card-text", "Manage categories"),
                    a(
                      href := "/admin/categories",
                      `class` := "btn btn-light",
                      "Go to Categories"
                    )
                  )
                )
              ),
              div(
                `class` := "col-md-4 mb-4",
                div(
                  `class` := "card bg-warning text-dark",
                  div(
                    `class` := "card-body",
                    h5(`class` := "card-title", "Orders"),
                    p(`class` := "card-text", "View and manage orders"),
                    a(
                      href := "/admin/orders",
                      `class` := "btn btn-light",
                      "Go to Orders"
                    )
                  )
                )
              )
            )
          )
        )
      )
    ).render
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 