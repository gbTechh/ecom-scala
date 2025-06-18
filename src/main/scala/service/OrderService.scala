package service

import cats.effect.IO
import model.{Order, OrderStatus}
import repository.OrderRepository

trait OrderService {
  def findById(id: Int): IO[Option[Order]]
  def findByUserId(userId: Int): IO[List[Order]]
  def create(order: Order): IO[Unit]
  def update(order: Order): IO[Unit]
  def delete(id: Int): IO[Unit]
  def updateStatus(id: Int, status: OrderStatus): IO[Unit]
}

object OrderService {
  def apply(repo: OrderRepository): OrderService = new OrderService {
    def findById(id: Int): IO[Option[Order]] = repo.findById(id)
    def findByUserId(userId: Int): IO[List[Order]] = repo.findByUserId(userId)
    def create(order: Order): IO[Unit] = for {
      _ <- validateOrder(order)
      _ <- repo.create(order)
    } yield ()
    def update(order: Order): IO[Unit] = for {
      _ <- validateOrder(order)
      _ <- repo.update(order)
    } yield ()
    def delete(id: Int): IO[Unit] = repo.delete(id)
    def updateStatus(id: Int, status: OrderStatus): IO[Unit] = repo.updateStatus(id, status)

    private def validateOrder(order: Order): IO[Unit] =
      IO.raiseError(new IllegalArgumentException("Order total amount must be positive"))
        .whenA(order.totalAmount <= 0)
  }
}