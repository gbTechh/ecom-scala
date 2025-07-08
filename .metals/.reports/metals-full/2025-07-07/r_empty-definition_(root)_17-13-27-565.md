error id: file://<WORKSPACE>/src/main/scala/model/User.scala:
file://<WORKSPACE>/src/main/scala/model/User.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -doobie/Timestamp#
	 -doobie/implicits/Timestamp#
	 -doobie/implicits/javasql/Timestamp#
	 -java/sql/Timestamp#
	 -Timestamp#
	 -scala/Predef.Timestamp#
offset: 491
uri: file://<WORKSPACE>/src/main/scala/model/User.scala
text:
```scala
package model

import doobie._
import doobie.implicits._
import doobie.implicits.javasql._ // CRÍTICO: Para soporte de java.sql.Timestamp
import java.sql.Timestamp

sealed trait UserRole
object UserRole {
  case object Admin extends UserRole
  case object Customer extends UserRole
}

case class User(
    id: Int,
    email: String,
    password: String,
    role: UserRole,
    firstName: Option[String],
    lastName: Option[String],
    status: Boolean,
    createdAt: java.sql.Timestamp@@,
    updatedAt: java.sql.Timestamp,
    deletedAt: Option[java.sql.Timestamp]
)

object User {
  implicit val userRead: Read[User] =
    Read[
      (
          Int, // id
          String, // email
          String, // password
          UserRole, // role
          Option[String], // first_name
          Option[String], // last_name
          Boolean, // status
          Timestamp, // created_at
          Timestamp, // updated_at
          Option[Timestamp] // deleted_at
      )
    ].map {
      case (
            id,
            email,
            pwd,
            role,
            fname,
            lname,
            status,
            created,
            updated,
            deleted
          ) =>
        User(
          id,
          email,
          pwd,
          role,
          fname,
          lname,
          status,
          created,
          updated,
          deleted
        )
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 