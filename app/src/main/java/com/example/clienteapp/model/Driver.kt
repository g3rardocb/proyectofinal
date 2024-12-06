package com.example.clienteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drivers")
data class Driver(
    @PrimaryKey val id: Int,
    val userId: Int,
    val name: String,
    val currentLatitude: Double,
    val currentLongitude: Double,
    val email: String,
    val phoneNumber: String
)
