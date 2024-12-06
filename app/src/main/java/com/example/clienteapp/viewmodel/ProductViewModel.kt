package com.example.clienteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.clienteapp.model.Product
import com.example.clienteapp.repository.ProductRepository
import kotlinx.coroutines.launch


class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepository(application)
    val products: MutableLiveData<List<Product>?> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun fetchProducts(restaurantId: Int) {
        viewModelScope.launch {
            try {
                val productList = repository.getProducts(restaurantId)
                products.postValue(productList)
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }

    fun addProductToCart(product: Product) {
        //repository.addProductToCart(product)
    }
}