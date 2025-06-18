package model

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
    createdAt: java.sql.Timestamp,
    updatedAt: java.sql.Timestamp,
    deletedAt: Option[java.sql.Timestamp]
)

object User {
  implicit val userRead: Read[User] =
    Read[
      (
        Int,               // id
        String,            // email
        String,            // password
        UserRole,          // role
        Option[String],    // first_name
        Option[String],    // last_name
        Boolean,           // status
        Timestamp,         // created_at
        Timestamp,         // updated_at
        Option[Timestamp]  // deleted_at
      )
    ].map {
      case (id, email,pwd, role, fname,lname, status,created,updated, deleted) =>
        User(id, email, pwd, role, fname, lname, status, created, updated, deleted)
    }
}