package com.example.clienteapp.repository

import android.content.Context
import com.example.clienteapp.database.AppDatabase
import com.example.clienteapp.model.User
import com.example.clienteapp.network.RetrofitInstance
import com.example.clienteapp.utils.TokenManager

class UserRepository(private val context: Context) {

    private val userDao = AppDatabase.getInstance(context).userDao()
    private val apiService = RetrofitInstance.apiService

    suspend fun login(email: String, password: String): User {
        val response = apiService.login(LoginRequest(email, password))

        if (response.isSuccessful) {
            val user = response.body()
            if (user != null) {
                TokenManager.saveToken(context, user.token)
                userDao.insertUser(user)
                return user
            } else {
                throw Exception("La respuesta no tiene cuerpo.")
            }
        } else {
            throw Exception("Error en el inicio de sesión. Código: ${response.code()}")
        }
    }

    suspend fun register(name: String, email: String, password: String): User {
        val response = apiService.registerUser(RegisterRequest(name, email, password))
        if (response.isSuccessful) {
            val user = response.body()!!
            TokenManager.saveToken(context, user.token)
            userDao.insertUser(user)
            return user
        } else {
            throw Exception("Error en el registro.")
        }
    }
}