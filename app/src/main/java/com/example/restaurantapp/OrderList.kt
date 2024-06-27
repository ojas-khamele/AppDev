package com.example.restaurantapp

data class FoodItem(val name: String, val price: Int, var quantity: Int)
class OrderList {
    companion object {
        var foodList = mutableListOf<FoodItem>()
    }
}