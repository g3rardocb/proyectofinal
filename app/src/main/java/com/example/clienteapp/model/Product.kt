package com.example.clienteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val restaurantId: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val photo: String // URL or file path for the product image
)


