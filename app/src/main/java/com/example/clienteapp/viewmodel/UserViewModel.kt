package com.example.clienteapp.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.clienteapp.model.User
import com.example.clienteapp.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)
    val userLiveData: MutableLiveData<User?> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val user = repository.login(email, password)
                userLiveData.postValue(user)
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val user = repository.register(name, email, password)
                userLiveData.postValue(user)
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}