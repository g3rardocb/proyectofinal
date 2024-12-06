package com.example.clienteapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clienteapp.databinding.FragmentMenuBinding
import com.example.clienteapp.view.adapters.ProductAdapter
import com.example.clienteapp.viewmodel.ProductViewModel

class MenuFragment : androidx.fragment.app.Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var productViewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter

    private var restaurantId: Int = 0

    companion object {
        private const val ARG_RESTAURANT_ID = "restaurant_id"

        fun newInstance(restaurantId: Int) = MenuFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_RESTAURANT_ID, restaurantId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restaurantId = it.getInt(ARG_RESTAURANT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        productViewModel.fetchProducts(restaurantId)
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter { product ->
            // Manejar el clic en un producto (agregar al carrito)
            productViewModel.addProductToCart(product)
        }

        binding.recyclerViewProducts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }

    private fun observeViewModel() {
        productViewModel.products.observe(viewLifecycleOwner) { products ->
            productAdapter.submitList(products)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}