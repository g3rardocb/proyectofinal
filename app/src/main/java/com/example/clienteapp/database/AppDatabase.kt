package com.example.clienteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clienteapp.database.Daos.ProductDao
import com.example.clienteapp.database.Daos.RestaurantDao
import com.example.clienteapp.database.Daos.UserDao
import com.example.clienteapp.model.Order
import com.example.clienteapp.model.Product
import com.example.clienteapp.model.Restaurant
import com.example.clienteapp.model.User

@Database(entities = [User::class, Restaurant::class, Product::class, Order::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun restaurantDao(): RestaurantDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
