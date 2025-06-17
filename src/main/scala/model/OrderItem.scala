package model

case class OrderItem(
    id: Int,
    orderId: Int, // id_order
    productId: Int, // id_product
    quantity: Int,
    unitPrice: Float,
    createdAt: java.sql.Timestamp,
    updatedAt: java.sql.Timestamp
)
