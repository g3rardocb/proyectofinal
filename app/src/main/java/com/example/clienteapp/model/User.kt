package com.example.clienteapp.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    val password: String,
    val userType: Int, // 1 for client, 2 for driver
    val email: String,
    @SerializedName("access_token") val token: String

)
