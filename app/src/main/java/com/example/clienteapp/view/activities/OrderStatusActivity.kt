package com.example.clienteapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.clienteapp.databinding.ActivityOrderStatusBinding
import com.example.clienteapp.viewmodel.OrderStatusViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class OrderStatusActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityOrderStatusBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var orderStatusViewModel: OrderStatusViewModel
    private var orderId: Int = 0 // Debes obtener el ID del pedido

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        orderStatusViewModel = ViewModelProvider(this).get(OrderStatusViewModel::class.java)

        // Inicializar el MapView
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)

        // Obtener el ID del pedido (por Intent o SharedPreferences)
        orderId = intent.getIntExtra("ORDER_ID", 0)

        observeViewModel()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        // Configurar el mapa (opciones, marcadores, etc.)
    }

    private fun observeViewModel() {
        orderStatusViewModel.order.observe(this) { order ->
            // Actualizar el estado del pedido y la ubicación del chofer
            // Por ejemplo, mover el marcador en el mapa
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
        orderStatusViewModel.startOrderStatusUpdates(orderId)
    }

    override fun onPause() {
        orderStatusViewModel.stopOrderStatusUpdates()
        binding.mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        binding.mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }
}