package com.example.clienteapp.repository

import com.example.clienteapp.model.ProductRequest

data class OrderRequest(
    val userId: Int,
    val products: List<ProductRequest>,
    val address: String
    // ... otros campos que se requieran
)
