package com.example.clienteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.clienteapp.model.Order
import com.example.clienteapp.model.Product
import com.example.clienteapp.repository.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = OrderRepository(application)
    val order: MutableLiveData<Order?> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getOrderDetails(orderId: Int) {
        viewModelScope.launch {
            try {
                val orderDetails = repository.getOrderDetails(orderId)
                order.postValue(orderDetails)
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }

    fun createOrder(products: List<Product>) {
        viewModelScope.launch {
            try {
                // Suponiendo que esta función retorna Response<Order>
                val response = repository.createOrder(products)

                if (response.isSuccessful) {
                    val newOrder = response.body()
                    if (newOrder != null) {
                        // 'order' debe ser MutableLiveData<Order>
                        order.postValue(newOrder)
                    } else {
                        // La respuesta fue exitosa pero no hay cuerpo
                        errorLiveData.postValue("La respuesta no contiene datos válidos.")
                    }
                } else {
                    // La respuesta no fue exitosa, podría ser un error del servidor
                    errorLiveData.postValue("Error al crear el pedido: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // Cualquier excepción de red, parsing, etc.
                errorLiveData.postValue("Excepción: ${e.message}")
            }
        }
    }
}