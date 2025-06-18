package repository

import cats.effect.IO
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
import model.Media
import model.Media._   // Instancias implícitas (mediaRead)

trait MediaRepository {
  def findById(id: Int): IO[Option[Media]]
  def findByProductId(productId: Int): IO[List[Media]]
  def create(media: Media): IO[Unit]
  def update(media: Media): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object MediaRepository {
  def apply(xa: Transactor[IO]): MediaRepository = new MediaRepository {

    def findById(id: Int): IO[Option[Media]] = {
      sql"""
        SELECT id, product_id, file_path, file_type, is_primary,
               created_at, updated_at, deleted_at
        FROM media
        WHERE id = $id AND deleted_at IS NULL
      """.query[Media].option.transact(xa)
    }

    def findByProductId(productId: Int): IO[List[Media]] = {
      sql"""
        SELECT id, product_id, file_path, file_type, is_primary,
               created_at, updated_at, deleted_at
        FROM media
        WHERE product_id = $productId AND deleted_at IS NULL
      """.query[Media].to[List].transact(xa)
    }

    def create(media: Media): IO[Unit] = {
      sql"""
        INSERT INTO media (product_id, file_path, file_type, is_primary)
        VALUES (${media.productId}, ${media.filePath}, ${media.fileType}, ${media.isPrimary})
      """.update.run.transact(xa).void
    }

    def update(media: Media): IO[Unit] = {
      sql"""
        UPDATE media
        SET product_id = ${media.productId},
            file_path  = ${media.filePath},
            file_type  = ${media.fileType},
            is_primary = ${media.isPrimary},
            updated_at = NOW()
        WHERE id = ${media.id}
      """.update.run.transact(xa).void
    }

    def delete(id: Int): IO[Unit] = {
      sql"""
        UPDATE media
        SET deleted_at = NOW()
        WHERE id = $id
      """.update.run.transact(xa).void
    }
  }
}