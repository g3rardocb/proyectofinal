package com.example.clienteapp.view.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clienteapp.R
import com.example.clienteapp.databinding.ItemRestaurantBinding
import com.example.clienteapp.model.Restaurant

class RestaurantAdapter(
    private val onItemClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private val restaurantList = mutableListOf<Restaurant>()

    fun submitList(restaurants: List<Restaurant>) {
        restaurantList.clear()
        restaurantList.addAll(restaurants)
        notifyDataSetChanged()
    }

    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.textViewRestaurantName.text = restaurant.name
            binding.textViewRestaurantDescription.text = restaurant.description

            // Cargar imagen con Glide
            Glide.with(binding.root.context)
                .load(restaurant.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageViewRestaurant)

            // Manejar clics
            binding.root.setOnClickListener {
                onItemClick(restaurant)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ItemRestaurantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurantList[position])
    }

    override fun getItemCount(): Int = restaurantList.size
}