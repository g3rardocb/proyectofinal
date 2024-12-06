package com.example.clienteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.clienteapp.model.Order
import com.example.clienteapp.network.RetrofitInstance
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class OrderStatusViewModel(application: Application) : AndroidViewModel(application) {

    val order: MutableLiveData<Order> = MutableLiveData()
    private var job: Job? = null

    fun startOrderStatusUpdates(orderId: Int) {
        job = viewModelScope.launch {
            while (isActive) {
                try {
                    val orderDetails = RetrofitInstance.apiService.getOrderDetails(orderId)
                    order.postValue(orderDetails)
                    delay(30000) // 30 segundos
                } catch (e: Exception) {
                    // Manejar errores
                }
            }
        }
    }

    fun stopOrderStatusUpdates() {
        job?.cancel()
    }
}