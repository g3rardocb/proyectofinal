package com.example.clienteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.clienteapp.model.Restaurant
import com.example.clienteapp.repository.RestaurantRepository
import kotlinx.coroutines.launch

class RestaurantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RestaurantRepository(application)
    val restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun fetchRestaurants() {
        viewModelScope.launch {
            try {
                val restaurantList = repository.getRestaurants()
                restaurants.postValue(restaurantList)
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}