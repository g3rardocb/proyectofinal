package com.example.clienteapp.model
import retrofit2.Response

data class ProductRequest(
    val productId: Int,
    val quantity: Int
)