package com.example.clienteapp.repository

import android.app.Application
import com.example.clienteapp.model.Order
import com.example.clienteapp.model.Product

class OrderRepository(application: Application) {
    fun getOrderDetails(orderId: Int): Order? {
        TODO("Not yet implemented")
    }

    fun createOrder(products: List<Product>): Any {
        TODO("Not yet implemented")
    }

}
