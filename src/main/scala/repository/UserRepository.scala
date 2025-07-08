package repository

import cats.effect.IO
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
import model.User
import model.User._ // trae userRead (y cualquier Meta/Write que hayas definido)

trait UserRepository {
  def findById(id: Int): IO[Option[User]]
  def findByEmail(email: String): IO[Option[User]]
  def create(user: User): IO[Unit]
  def update(user: User): IO[Unit]
  def delete(id: Int): IO[Unit]
  def createAndReturnId(user: User): IO[Int]
}

object UserRepository {
  def apply(xa: Transactor[IO]): UserRepository = new UserRepository {

    def findById(id: Int): IO[Option[User]] = {
      sql"""
        SELECT id, email, password, role,
               first_name, last_name, status,
               created_at, updated_at, deleted_at
        FROM users
        WHERE id = $id AND deleted_at IS NULL
      """.query[User].option.transact(xa)
    }

    def findByEmail(email: String): IO[Option[User]] = {
      sql"""
        SELECT id, email, password, role,
               first_name, last_name, status,
               created_at, updated_at, deleted_at
        FROM users
        WHERE email = $email AND deleted_at IS NULL
      """.query[User].option.transact(xa)
    }

    def create(user: User): IO[Unit] = {
      sql"""
        INSERT INTO users
          (email, password, role, first_name, last_name, status)
        VALUES
          (${user.email},
           ${user.password},
           ${user.role.toString.toLowerCase},   -- asume VARCHAR 'admin'/'customer'
           ${user.firstName},
           ${user.lastName},
           ${user.status})
      """.update.run.transact(xa).void
    }

    def update(user: User): IO[Unit] = {
      sql"""
        UPDATE users
        SET email      = ${user.email},
            password   = ${user.password},
            role       = ${user.role.toString.toLowerCase},
            first_name = ${user.firstName},
            last_name  = ${user.lastName},
            status     = ${user.status},
            updated_at = NOW()
        WHERE id = ${user.id}
      """.update.run.transact(xa).void
    }

    def delete(id: Int): IO[Unit] = {
      sql"""
        UPDATE users
        SET deleted_at = NOW()
        WHERE id = $id
      """.update.run.transact(xa).void
    }
    def createAndReturnId(user: User): IO[Int] = {
      sql"""
      INSERT INTO users (email, password, role, first_name, last_name, status)
      VALUES (
        ${user.email},
        ${user.password},
        ${user.role.toString.toLowerCase},
        ${user.firstName},
        ${user.lastName},
        ${user.status}
      )
    """.update.withUniqueGeneratedKeys[Int]("id").transact(xa)
    }
  }
}
