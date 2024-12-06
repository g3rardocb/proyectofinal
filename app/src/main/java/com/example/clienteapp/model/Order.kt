package com.example.clienteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(tableName = "orders")
@TypeConverters(Converters::class)
data class Order(
    @PrimaryKey val id: Int,
    val userId: Int,
    val driverId: Int?,
    val restaurantId: Int,
    val products: List<Product>,
    val orderDateTime: String,
    val status: OrderStatus, // 0: requested, 1: accepted, 2: on the way, 3: delivered
    val deliveryAddress: String,
    val deliveryLatitude: Double,
    val deliveryLongitude: Double,
    val totalAmount: Double,
    val timestamp: Long
)




