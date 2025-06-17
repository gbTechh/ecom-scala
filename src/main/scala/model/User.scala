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
