package com.example.clienteapp.network

import com.example.clienteapp.model.Order
import com.example.clienteapp.model.Product
import com.example.clienteapp.model.Restaurant
import com.example.clienteapp.model.User
import com.example.clienteapp.repository.LoginRequest
import com.example.clienteapp.repository.OrderRequest
import com.example.clienteapp.repository.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @POST("users/login")
    suspend fun login(@Body loginRequest: LoginRequest): retrofit2.Response<User>

    @POST("users")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): retrofit2.Response<User>

    @GET("users/me")
    suspend fun getCurrentUser(): User

    @GET("restaurants")
    suspend fun getRestaurants(): retrofit2.Response<List<Restaurant>>

    @GET("restaurants/{id}/products")
    suspend fun getProducts(@Path("id") id: Int): List<Product>

    @POST("orders")
    suspend fun createOrder(@Body orderRequest: OrderRequest): retrofit2.Response<Order>

    @GET("orders/{id}")
    suspend fun getOrderDetails(@Path("id") id: Int): Order


    @Multipart
    @POST("orders/{id}/deliverproof")
    suspend fun uploadDeliveryProof(
        @Path("id") orderId: Int,
        @Part image: MultipartBody.Part
    ): ResponseBody

    // Otros endpoints...
}