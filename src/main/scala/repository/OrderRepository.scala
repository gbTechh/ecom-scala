package repository

import cats.effect.IO
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
import model.Order
import model.Order._
import model.OrderStatus

trait OrderRepository {
  def createAndGetId(order: Order): IO[Int]
  def findAll(): IO[List[Order]]
  def findById(id: Int): IO[Option[Order]]
  def findByUserId(userId: Int): IO[List[Order]]
  def create(order: Order): IO[Unit]
  def update(order: Order): IO[Unit]
  def delete(id: Int): IO[Unit]
  def updateStatus(orderId: Int, status: OrderStatus): IO[Unit]
}

object OrderRepository {
  def apply(xa: Transactor[IO]): OrderRepository = new OrderRepository {
    def createAndGetId(order: Order): IO[Int] = {
      sql"""
    INSERT INTO orders (id_user, total_amount, status)
    VALUES (${order.userId}, ${order.totalAmount}, ${order.status})
  """.update
        .withUniqueGeneratedKeys[Int]("id")
        .transact(xa)
    }
    def findAll(): IO[List[Order]] =
      sql"""
        SELECT *
        FROM orders
        WHERE deleted_at IS NULL
      """.query[Order].to[List].transact(xa)
    def findById(id: Int): IO[Option[Order]] = {
      sql"""
        SELECT id, id_user, total_amount, status, created_at, updated_at, deleted_at 
        FROM orders 
        WHERE id = $id AND deleted_at IS NULL
      """.query[Order].option.transact(xa)
    }

    def findByUserId(userId: Int): IO[List[Order]] = {
      sql"""
        SELECT id, id_user, total_amount, status, created_at, updated_at, deleted_at 
        FROM orders 
        WHERE id_user = $userId AND deleted_at IS NULL
      """.query[Order].to[List].transact(xa)
    }

    def create(order: Order): IO[Unit] = {
      sql"""
        INSERT INTO orders (id_user, total_amount, status)
        VALUES (${order.userId}, ${order.totalAmount}, ${order.status})
      """.update.run.transact(xa).void
    }

    def update(order: Order): IO[Unit] = {
      sql"""
        UPDATE orders
        SET id_user = ${order.userId}, 
            total_amount = ${order.totalAmount}, 
            status = ${order.status},
            updated_at = NOW()
        WHERE id = ${order.id}
      """.update.run.transact(xa).void
    }

    def delete(id: Int): IO[Unit] = {
      sql"UPDATE orders SET deleted_at = NOW() WHERE id = $id".update.run
        .transact(xa)
        .void
    }

    def updateStatus(orderId: Int, status: OrderStatus): IO[Unit] = {
      sql"""
        UPDATE orders
        SET status = $status,
            updated_at = NOW()
        WHERE id = $orderId
      """.update.run.transact(xa).void
    }
  }
}
