package com.example.clienteapp.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.clienteapp.MainActivity
import com.example.clienteapp.databinding.ActivityRegisterBinding
import com.example.clienteapp.viewmodel.UserViewModel
import com.example.clienteapp.utils.Extensions.showToast

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.buttonRegister.setOnClickListener {
            val name = binding.editTextName.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            if (password != confirmPassword) {
                showToast("Las contraseñas no coinciden.")
            } else if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                userViewModel.register(name, email, password)
            } else {
                showToast("Por favor, completa todos los campos.")
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        userViewModel.userLiveData.observe(this) { user ->
            if (user != null) {
                // Registro exitoso, navegar a MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showToast("Error en el registro.")
            }
        }
    }
}