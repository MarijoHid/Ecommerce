package com.example.ecommerceapp
import org.junit.Test
import org.junit.Assert.*

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val currency: String,
    val inStock: Boolean
)

// Función que determina si se pueden mostrar productos
fun canDisplayProducts(productList: List<Product>): Boolean {
    return productList.isNotEmpty()
}

class ProductDisplayTest {

    @Test
    fun `given a product list, should allow displaying products`() {
        val products = listOf(
            Product(1, "iPhone 13", "The latest iPhone from Apple", 999.99, "USD", true),
            Product(2, "Samsung Galaxy S21", "The latest Samsung phone", 899.99, "USD", true)
        )
        assertTrue(canDisplayProducts(products))
    }

    @Test
    fun `given an empty product list, should not allow displaying products`() {
        val emptyList = emptyList<Product>()
        assertFalse(canDisplayProducts(emptyList))
    }
}