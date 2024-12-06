package com.example.clienteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class Restaurant(
    @PrimaryKey val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val ownerId: Int,
    val description: String,
    val logo: String, // URL or file path for the logo
    val imageUrl: String,
    val address: String
)



