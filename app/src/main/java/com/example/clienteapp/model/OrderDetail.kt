package com.example.clienteapp.model

data class OrderDetail(
    val id: Int,
    val productId: Int,
    val orderId: Int,
    val quantity: Int,
    val productPrice: Double
)