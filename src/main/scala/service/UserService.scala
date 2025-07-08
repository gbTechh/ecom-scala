package service

import cats.effect.IO
import cats.syntax.all._
import model.User
import repository.UserRepository

trait UserService {
  def findById(id: Int): IO[Option[User]]
  def findByEmail(email: String): IO[Option[User]]
  def createUser(user: User): IO[Unit]
  def update(user: User): IO[Unit]
  def delete(id: Int): IO[Unit]
  def createAndReturnId(user: User): IO[Int]
}

object UserService {
  def apply(repo: UserRepository): UserService = new UserService {
    def createAndReturnId(user: User): IO[Int] =
      for {
        _ <- validateUser(user)
        id <- repo.createAndReturnId(user)
      } yield id
    def findById(id: Int): IO[Option[User]] =
      repo.findById(id)

    def findByEmail(email: String): IO[Option[User]] =
      repo.findByEmail(email)

    def createUser(user: User): IO[Unit] =
      for {
        _ <- validateUser(user)
        _ <- repo.create(user)
      } yield ()

    def update(user: User): IO[Unit] =
      for {
        _ <- validateUser(user)
        _ <- repo.update(user)
      } yield ()

    def delete(id: Int): IO[Unit] =
      repo.delete(id)

    /** Validaciones básicas antes de persistir o modificar un usuario. */
    private def validateUser(user: User): IO[Unit] =
      for {
        _ <- IO
          .raiseError(
            new IllegalArgumentException("Email cannot be empty")
          )
          .whenA(user.email.trim.isEmpty)
        _ <- IO
          .raiseError(
            new IllegalArgumentException("Password cannot be empty")
          )
          .whenA(user.password.trim.isEmpty)
      } yield ()
  }
}
