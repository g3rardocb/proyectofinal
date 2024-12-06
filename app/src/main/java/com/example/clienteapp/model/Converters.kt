package com.example.clienteapp.model

import androidx.room.TypeConverter
import com.example.choferapp.model.OrderStatus
import com.example.choferapp.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun fromProductList(products: List<Product>): String {
        val gson = Gson()
        return gson.toJson(products)
    }

    @TypeConverter
    fun toProductList(productsString: String): List<Product> {
        val gson = Gson()
        val listType: Type = object : TypeToken<List<Product>>() {}.type
        return gson.fromJson(productsString, listType)
    }

    @TypeConverter
    fun fromOrderStatus(status: OrderStatus): String {
        return status.name
    }

    @TypeConverter
    fun toOrderStatus(statusString: String): OrderStatus {
        return OrderStatus.valueOf(statusString)
    }
}