package repository

import cats.effect.IO
import model.Cart
import java.util.UUID

trait CartRepository {
  def create(cart: Cart): IO[Unit]
  def findById(id: UUID): IO[Option[Cart]]
  def update(cart: Cart): IO[Unit]
  def delete(id: UUID): IO[Unit]
  def findByUserId(userId: Int): IO[Option[Cart]]
}

class InMemoryCartRepository extends CartRepository {
  private var carts: Map[UUID, Cart] = Map.empty

  def create(cart: Cart): IO[Unit] = IO {
    carts = carts + (cart.id -> cart)
  }

  def findById(id: UUID): IO[Option[Cart]] = IO {
    carts.get(id)
  }

  def update(cart: Cart): IO[Unit] = IO {
    carts = carts + (cart.id -> cart)
  }

  def delete(id: UUID): IO[Unit] = IO {
    carts = carts - id
  }

  def findByUserId(userId: Int): IO[Option[Cart]] = IO {
    carts.values.find(_.userId.contains(userId))
  }
}
