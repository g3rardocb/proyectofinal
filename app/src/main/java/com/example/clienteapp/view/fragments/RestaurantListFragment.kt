package com.example.clienteapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clienteapp.R
import com.example.clienteapp.databinding.FragmentRestaurantListBinding
import com.example.clienteapp.view.adapters.RestaurantAdapter
import com.example.clienteapp.viewmodel.RestaurantViewModel

class RestaurantListFragment : androidx.fragment.app.Fragment() {

    private var _binding: FragmentRestaurantListBinding? = null
    private val binding get() = _binding!!

    private lateinit var restaurantViewModel: RestaurantViewModel
    private lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRestaurantListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        restaurantViewModel.fetchRestaurants()
    }

    private fun setupRecyclerView() {
        restaurantAdapter = RestaurantAdapter { restaurant ->
            // Manejar el clic en un restaurante
            // Navegar al MenuFragment
            val menuFragment = MenuFragment.newInstance(restaurant.id)
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, menuFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerViewRestaurants.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = restaurantAdapter
        }
    }

    private fun observeViewModel() {
        restaurantViewModel.restaurants.observe(viewLifecycleOwner) { restaurants ->
            restaurantAdapter.submitList(restaurants)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}