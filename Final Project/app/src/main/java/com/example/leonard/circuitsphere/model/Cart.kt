package com.example.leonard.circuitsphere

import com.example.leonard.circuitsphere.model.Product

object Cart {
    private val cartItems = mutableListOf<Product>()

    fun addProduct(product: Product) {
        cartItems.add(product)
    }

    fun getCartItems(): List<Product> {
        return cartItems
    }

    fun removeFromCart(product: Product) {
        cartItems.remove(product)
    }

    fun clearCart() {
        cartItems.clear()
    }
}