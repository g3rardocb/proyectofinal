package com.example.clienteapp.repository

import android.content.Context
import com.example.clienteapp.database.AppDatabase
import com.example.clienteapp.model.Restaurant
import com.example.clienteapp.network.RetrofitInstance

class RestaurantRepository(private val context: Context) {

    private val restaurantDao = AppDatabase.getInstance(context).restaurantDao()
    private val apiService = RetrofitInstance.apiService

    suspend fun getRestaurants(): List<Restaurant> {
        val response = apiService.getRestaurants()
        return if (response.isSuccessful) {
            val restaurantList = response.body() ?: emptyList() // en caso de que body sea null, usar lista vacía
            restaurantDao.insertRestaurants(restaurantList)
            restaurantList
        } else {
            // Si hay un error, intentar obtener datos locales
            restaurantDao.getAllRestaurants()
        }
    }
}