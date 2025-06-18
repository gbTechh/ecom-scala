error id: file://<WORKSPACE>/src/main/scala/http/views/CategoryView.scala:main/http/
file://<WORKSPACE>/src/main/scala/http/views/CategoryView.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 18
uri: file://<WORKSPACE>/src/main/scala/http/views/CategoryView.scala
text:
```scala
package http.views@@

import scalatags.Text.all._
import model.Category

object CategoryView {
  def renderCategory(category: Category): String =
    html(
      head(
        title := "E-commerce - Categoría",
        meta(charset := "UTF-8")
      ),
      body(
        h1("Categoría: ", category.name),
        p("Slug: ", category.slug),
        p("Descripción: ", category.description.getOrElse("Sin descripción")),
        p("Estado: ", if (category.status) "Activa" else "Inactiva"),
        a(href := "/categories")("Volver a categorías")
      )
    ).toString()
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 