package service

import cats.effect.IO
import model.{Cart, CartItem, Product}
import repository.{CartRepository, ProductRepository}
import java.util.UUID
import cats.implicits._

class CartService(
    cartRepo: CartRepository,
    productRepo: ProductRepository
) {
  def createCart(userId: Option[Int]): IO[Cart] = {
    val newCart = Cart(
      id = UUID.randomUUID(),
      userId = userId,
      items = List.empty,
      createdAt = System.currentTimeMillis()
    )
    cartRepo.create(newCart).map(_ => newCart)
  }

  def addItem(cartId: UUID, productId: Int, quantity: Int): IO[Cart] = {
    for {
      maybeCart <- cartRepo.findById(cartId)
      maybeProduct <- productRepo.findById(productId)
      cart <- maybeCart match {
        case Some(cart) =>
          maybeProduct match {
            case Some(product) =>
              val newItem =
                CartItem(productId, quantity, product.price.getOrElse(0f))
              val updatedItems = newItem :: cart.items
              val updatedCart = cart.copy(items = updatedItems)
              cartRepo.update(updatedCart).map(_ => updatedCart)
            case None =>
              IO.raiseError(new IllegalArgumentException("Product not found"))
          }
        case None =>
          IO.raiseError(new IllegalArgumentException("Cart not found"))
      }
    } yield cart
  }

  def removeItem(cartId: UUID, productId: Int): IO[Cart] = {
    for {
      maybeCart <- cartRepo.findById(cartId)
      cart <- maybeCart match {
        case Some(cart) =>
          val updatedItems = cart.items.filterNot(_.productId == productId)
          val updatedCart = cart.copy(items = updatedItems)
          cartRepo.update(updatedCart).map(_ => updatedCart)
        case None =>
          IO.raiseError(new IllegalArgumentException("Cart not found"))
      }
    } yield cart
  }

  def getCart(cartId: UUID): IO[Option[Cart]] = {
    cartRepo.findById(cartId)
  }

  def clearCart(cartId: UUID): IO[Unit] = {
    for {
      maybeCart <- cartRepo.findById(cartId)
      _ <- maybeCart match {
        case Some(cart) =>
          cartRepo.update(cart.copy(items = List.empty))
        case None =>
          IO.unit
      }
    } yield ()
  }

  def getCartWithProducts(cartId: UUID): IO[Option[(Cart, List[Product])]] = {
    for {
      maybeCart <- cartRepo.findById(cartId)
      maybeProducts <- maybeCart match {
        case Some(cart) =>
          cart.items
            .traverse(item => productRepo.findById(item.productId))
            .map(productsOpt => Some(productsOpt.flatten))
        case None => IO.pure(None)
      }
    } yield maybeCart.map(_ -> maybeProducts.getOrElse(List.empty))
  }

}
