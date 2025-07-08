error id: file://<WORKSPACE>/src/main/scala/repository/OrderRepository.scala:
file://<WORKSPACE>/src/main/scala/repository/OrderRepository.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 644
uri: file://<WORKSPACE>/src/main/scala/repository/OrderRepository.scala
text:
```scala
package repository

import cats.effect.IO
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
import model.Order
import model.Order._
import model.OrderStatus

trait OrderRepository {
  def findById(id: Int): IO[Option[Order]]
  def findByUserId(userId: Int): IO[List[Order]]
  def create(order: Order): IO[Unit]
  def update(order: Order): IO[Unit]
  def delete(id: Int): IO[Unit]
  def updateStatus(orderId: Int, status: OrderStatus): IO[Unit]
}

object OrderRepository {
  def apply(xa: Transactor[IO]): OrderRepository = new OrderRepository {
    def findAll(): IO[List[Product]] =
      sql"""
        SELECT@@ *
        FROM products
        WHERE deleted_at IS NULL
      """.query[Product].to[List].transact(xa)
    def findById(id: Int): IO[Option[Order]] = {
      sql"""
        SELECT id, user_id, total_amount, status, created_at, updated_at, deleted_at 
        FROM orders 
        WHERE id = $id AND deleted_at IS NULL
      """.query[Order].option.transact(xa)
    }

    def findByUserId(userId: Int): IO[List[Order]] = {
      sql"""
        SELECT id, user_id, total_amount, status, created_at, updated_at, deleted_at 
        FROM orders 
        WHERE user_id = $userId AND deleted_at IS NULL
      """.query[Order].to[List].transact(xa)
    }

    def create(order: Order): IO[Unit] = {
      sql"""
        INSERT INTO orders (user_id, total_amount, status)
        VALUES (${order.userId}, ${order.totalAmount}, ${order.status})
      """.update.run.transact(xa).void
    }

    def update(order: Order): IO[Unit] = {
      sql"""
        UPDATE orders
        SET user_id = ${order.userId}, 
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

```


#### Short summary: 

empty definition using pc, found symbol in pc: 