package service

import cats.effect.IO
import cats.syntax.all._
import model.Media
import repository.MediaRepository

trait MediaService {
  def findById(id: Int): IO[Option[Media]]
  def findByProductId(productId: Int): IO[List[Media]]
  def create(media: Media): IO[Unit]
  def update(media: Media): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object MediaService {
  def apply(repo: MediaRepository): MediaService = new MediaService {

    def findById(id: Int): IO[Option[Media]] =
      repo.findById(id)

    def findByProductId(productId: Int): IO[List[Media]] =
      repo.findByProductId(productId)

    def create(media: Media): IO[Unit] =
      for {
        _ <- validateMedia(media)
        _ <- repo.create(media)
      } yield ()

    def update(media: Media): IO[Unit] =
      for {
        _ <- validateMedia(media)
        _ <- repo.update(media)
      } yield ()

    def delete(id: Int): IO[Unit] =
      repo.delete(id)

    /** Validaciones mínimas antes de persistir. */
    private def validateMedia(media: Media): IO[Unit] =
      IO.raiseError(
        new IllegalArgumentException("Media filePath cannot be empty")
      ).whenA(media.filePath.isEmpty)
  }
}