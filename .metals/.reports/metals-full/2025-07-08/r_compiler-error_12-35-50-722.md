error id: 869BFEA1D55D3BA5F2C5316464F9ABAF
file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
### scala.reflect.internal.FatalError: 
  unexpected tree: class scala.reflect.internal.Trees$CaseDef
case None => Category(id = id, name = form.getFirstOrElse("name", ""), slug = form.getFirstOrElse("slug", ""), description = Option(form.getFirstOrElse("description", "")).filter(((x$6) => x$6.nonEmpty)), status = form.getFirstOrElse("status", "false").toBoolean, createdAt = now, updatedAt = now, deletedAt = None)
     while compiling: file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.12.18
    compiler version: version 2.12.18
  reconstructed args: -classpath <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.12.18/scala-library-2.12.18.jar -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: CaseDef
       tree position: line 81 of file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
              symbol: null
           call site: <none> in <none>

== Source file context for tree position ==

    78                 updatedAt = now,
    79                 deletedAt = None
    80               )
    81             case None =>
    82               // Si no existe, creamos una nueva (aunque esto no debería ocurrir)
    83               Category(
    84                 id = id,

occurred in the presentation compiler.



action parameters:
offset: 3065
uri: file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
text:
```scala
package http.controllers.admin

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.headers.Location
import org.http4s.headers.`Content-Type`
import service.CategoryService
import java.sql.Timestamp
import java.time.Instant
import model.Category
import _root_.http.views.admin.CategoryAdminView
import org.http4s.MediaType

class CategoryAdminController(categoryService: CategoryService) {
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    // Listar categorías (GET /admin/categories)
    case GET -> Root =>
      categoryService
        .findAll()
        .flatMap(categories =>
          Ok(CategoryAdminView.list(categories))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        )

    // Mostrar formulario de añadir (GET /admin/categories/add)
    case GET -> Root / "add" =>
      Ok(CategoryAdminView.addForm())
        .map(_.withContentType(`Content-Type`(MediaType.text.html)))

    // Mostrar formulario de edición (GET /admin/categories/edit/:id)
    case GET -> Root / "edit" / IntVar(id) =>
      categoryService.findById(id).flatMap {
        case Some(category) =>
          Ok(CategoryAdminView.editForm(category))
            .map(_.withContentType(`Content-Type`(MediaType.text.html)))
        case None =>
          NotFound("Category not found")
      }

    // Procesar añadir categoría (POST /admin/categories/add)
    case req @ POST -> Root / "add" =>
      req.as[UrlForm].flatMap { form =>
        val now = Timestamp.from(Instant.now())
        val category = Category(
          id = 0,
          name = form.getFirstOrElse("name", ""),
          slug = form.getFirstOrElse("slug", ""),
          description =
            Option(form.getFirstOrElse("description", "")).filter(_.nonEmpty),
          status = form.getFirstOrElse("status", "false").toBoolean,
          createdAt = now,
          updatedAt = now,
          deletedAt = None
        )
        categoryService.create(category) *>
          SeeOther(Location(uri"/admin/categories"))
      }
    case req @ POST -> Root / "edit" / IntVar(id) =>
      req.as[UrlForm].flatMap { form =>
        for {
          // Primero obtenemos la categoría existente para preservar createdAt
          existing <- categoryService.findById(id)
          now = Timestamp.from(Instant.now())

          updatedCategory = existing match {
            case Some(c) =>
              Category(
                id = id,
                name = form.getFirstOrElse("name", c.name),
                slug = form.getFirstOrElse("slug", c.slug),
                description = Option(form.getFirstOrElse("description", ""))
                  .filter(_.nonEmpty),
                status =
                  form.getFirstOrElse("status", c.status.toString).toBoolean,
                createdAt = c.createdAt, // Mantenemos la fecha original
                updatedAt = now,
                deletedAt = None
              )
            case None =>
              // Si no existe, creamos una nueva (aunque @@esto no debería ocurrir)
              Category(
                id = id,
                name = form.getFirstOrElse("name", ""),
                slug = form.getFirstOrElse("slug", ""),
                description = Option(form.getFirstOrElse("description", ""))
                  .filter(_.nonEmpty),
                status = form.getFirstOrElse("status", "false").toBoolean,
                createdAt = now,
                updatedAt = now,
                deletedAt = None
              )
          }

          _ <- categoryService.update(updatedCategory)
          response <- SeeOther(Location(uri"/admin/categories"))
        } yield response
      }
    // Eliminar categoría (POST /admin/categories/delete/:id)
    case POST -> Root / "delete" / IntVar(id) =>
      categoryService.delete(id) *>
        SeeOther(Location(uri"/admin/categories"))
  }
}

```


presentation compiler configuration:
Scala version: 2.12.18
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.12.18/scala-library-2.12.18.jar [exists ]
Options:





#### Error stacktrace:

```
scala.reflect.internal.Reporting.abort(Reporting.scala:69)
	scala.reflect.internal.Reporting.abort$(Reporting.scala:65)
	scala.reflect.internal.SymbolTable.abort(SymbolTable.scala:28)
	scala.tools.nsc.typechecker.Typers$Typer.typedOutsidePatternMode$1(Typers.scala:5760)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5777)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5812)
	scala.tools.nsc.typechecker.Typers$Typer.typedQualifier(Typers.scala:5896)
	scala.meta.internal.pc.PcDefinitionProvider.definitionTypedTreeAt(PcDefinitionProvider.scala:190)
	scala.meta.internal.pc.PcDefinitionProvider.definition(PcDefinitionProvider.scala:69)
	scala.meta.internal.pc.PcDefinitionProvider.definition(PcDefinitionProvider.scala:17)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$definition$1(ScalaPresentationCompiler.scala:479)
	scala.meta.internal.pc.CompilerAccess.retryWithCleanCompiler(CompilerAccess.scala:182)
	scala.meta.internal.pc.CompilerAccess.$anonfun$withSharedCompiler$1(CompilerAccess.scala:155)
	scala.Option.map(Option.scala:230)
	scala.meta.internal.pc.CompilerAccess.withSharedCompiler(CompilerAccess.scala:154)
	scala.meta.internal.pc.CompilerAccess.$anonfun$withNonInterruptableCompiler$1(CompilerAccess.scala:132)
	scala.meta.internal.pc.CompilerAccess.$anonfun$onCompilerJobQueue$1(CompilerAccess.scala:209)
	scala.meta.internal.pc.CompilerJobQueue$Job.run(CompilerJobQueue.scala:152)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	java.base/java.lang.Thread.run(Thread.java:840)
```
#### Short summary: 

scala.reflect.internal.FatalError: 
  unexpected tree: class scala.reflect.internal.Trees$CaseDef
case None => Category(id = id, name = form.getFirstOrElse("name", ""), slug = form.getFirstOrElse("slug", ""), description = Option(form.getFirstOrElse("description", "")).filter(((x$6) => x$6.nonEmpty)), status = form.getFirstOrElse("status", "false").toBoolean, createdAt = now, updatedAt = now, deletedAt = None)
     while compiling: file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.12.18
    compiler version: version 2.12.18
  reconstructed args: -classpath <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.12.18/scala-library-2.12.18.jar -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: CaseDef
       tree position: line 81 of file://<WORKSPACE>/src/main/scala/http/controllers/CategoryAdminController.scala
              symbol: null
           call site: <none> in <none>

== Source file context for tree position ==

    78                 updatedAt = now,
    79                 deletedAt = None
    80               )
    81             case None =>
    82               // Si no existe, creamos una nueva (aunque esto no debería ocurrir)
    83               Category(
    84                 id = id,