package com.example.clienteapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.clienteapp.databinding.FragmentOrderDetailBinding
import com.example.clienteapp.viewmodel.OrderViewModel

class OrderDetailFragment : androidx.fragment.app.Fragment() {

    private var _binding: FragmentOrderDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var orderViewModel: OrderViewModel
    private var orderId: Int = 0

    companion object {
        private const val ARG_ORDER_ID = "order_id"

        fun newInstance(orderId: Int) = OrderDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ORDER_ID, orderId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            orderId = it.getInt(ARG_ORDER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        observeViewModel()

        orderViewModel.getOrderDetails(orderId)
    }

    private fun observeViewModel() {
        orderViewModel.order.observe(viewLifecycleOwner) { order ->
            // Actualizar la interfaz con los detalles del pedido
            binding.textViewOrderStatus.text = order?.status!!.name
            // Mostrar productos, total, etc.
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}