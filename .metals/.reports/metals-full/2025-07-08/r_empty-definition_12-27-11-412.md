error id: file://<WORKSPACE>/src/main/scala/http/controllers/SessionManager.scala:
file://<WORKSPACE>/src/main/scala/http/controllers/SessionManager.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 142
uri: file://<WORKSPACE>/src/main/scala/http/controllers/SessionManager.scala
text:
```scala
import org.http4s.session.SessionManager
import java.util.UUID

val sessionManager = SessionManager[IO, UUID](
  key = "cartId",
  secret = "y@@our-secret-key-here", // Debería ser una clave segura
  maxAge = Some(30.days) // Duración de la sesión
)

```


#### Short summary: 

empty definition using pc, found symbol in pc: 