package model

import java.util.UUID

case class CartItem(
    productId: Int,
    quantity: Int,
    unitPrice: Float
)

case class Cart(
    id: UUID, // Identificador único del carrito
    userId: Option[Int], // Usuario si está logueado
    items: List[CartItem],
    createdAt: Long // Timestamp
)
