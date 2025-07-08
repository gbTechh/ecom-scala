package model

import doobie._
import doobie.implicits._
import doobie.implicits.javasql._
import java.sql.Timestamp

sealed trait UserRole
object UserRole {
  case object Admin extends UserRole
  case object Customer extends UserRole

  // Conversión explícita para evitar problemas de varianza
  implicit val userRoleMeta: Meta[UserRole] = {
    val fromString: String => UserRole = {
      case "admin"    => Admin
      case "customer" => Customer
      case other =>
        throw new IllegalArgumentException(s"Unknown UserRole: $other")
    }

    val toString: UserRole => String = {
      case Admin    => "admin"
      case Customer => "customer"
    }

    Meta[String].timap(fromString)(toString)
  }
}

case class User(
    id: Int,
    email: String,
    password: String,
    role: UserRole,
    firstName: Option[String],
    lastName: Option[String],
    status: Boolean,
    createdAt: Timestamp,
    updatedAt: Timestamp,
    deletedAt: Option[Timestamp]
)

object User {
  implicit val userRead: Read[User] = {
    // Asegurar que todas las instancias necesarias están en ámbito
    implicit val roleMeta: Meta[UserRole] = UserRole.userRoleMeta

    Read[
      (
          Int,
          String,
          String,
          UserRole, // Usamos UserRole directamente gracias a la instancia Meta
          Option[String],
          Option[String],
          Boolean,
          Timestamp,
          Timestamp,
          Option[Timestamp]
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
}
