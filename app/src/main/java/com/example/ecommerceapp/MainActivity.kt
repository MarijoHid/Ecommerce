package com.example.ecommerceapp

import com.example.ecommerceapp.ProductResponse
import com.example.ecommerceapp.ProductService


import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        obtenerProductos()
    }

    private fun obtenerProductos() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonkeeper.com/") // Asegurate de usar una URL válida
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ProductService::class.java)

        val call = service.getProducts()

        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    val products = response.body()?.products
                    products?.forEach { product ->
                        Log.d("MainActivity", "Product name: ${product.name} precio: ${product.price}")
                    }
                } else {
                    Log.e("MainActivity", "Respuesta no exitosa: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("MainActivity", "Error al obtener productos", t)
            }
        })
    }
}