package com.example.restaurantapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Index

class UserOrder : AppCompatActivity(){
    private lateinit var dbHelper : DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_order_layout)

        dbHelper = DatabaseHelper(this)
        val linearLayout: LinearLayout = findViewById(R.id.linearLayout)

        val backBtn = findViewById<Button>(R.id.btnBack6)

        backBtn.setOnClickListener{
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        putList(linearLayout)
    }

    private fun putList(linearLayout: LinearLayout) {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            DatabaseHelper.COLUMN_ITEM_NAME,
            DatabaseHelper.COLUMN_ITEM_PRICE
        )

        val cursor = db.query(
            DatabaseHelper.TABLE_FOOD_ITEMS,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val itemList = mutableListOf<FoodItem>()
        with(cursor) {
            while (moveToNext()) {
                val itemName =
                    getString(getColumnIndexOrThrow(com.example.restaurantapp.DatabaseHelper.COLUMN_ITEM_NAME))
                val itemPrice =
                    getInt(getColumnIndexOrThrow(com.example.restaurantapp.DatabaseHelper.COLUMN_ITEM_PRICE))
                itemList.add(FoodItem(itemName, itemPrice, 1))
            }
        }
        cursor.close()

        itemList.forEach { item ->
            val textView = TextView(this).apply {
                text = "Item : ${item.name}, Price : ${item.price}"
                textSize = 18f
                setPadding(16, 16, 16, 16)
                setOnClickListener {
                    addToOrder(item)
                }
            }
            linearLayout.addView(textView)
        }
    }


    private fun addToOrder(foodItem: FoodItem) {

        Toast.makeText(this, "Select Quantity", Toast.LENGTH_SHORT).show()

        val btn = findViewById<Button>(R.id.btnAdd)
        btn.setOnClickListener {

            val qtyEditText = findViewById<EditText>(R.id.etQty)
            qtyEditText.visibility = View.VISIBLE
            val qtyText = qtyEditText.text.toString()
            val quantity = qtyText.toInt()

            foodItem.quantity = quantity
            if(quantity>0) {
                Toast.makeText(this, "${foodItem.name} Quantity : ${foodItem.quantity} added to order", Toast.LENGTH_SHORT).show()
                OrderList.foodList.add(foodItem)
            }
            else{
                Toast.makeText(this, "Invalid quantity", Toast.LENGTH_SHORT).show()
            }
            qtyEditText.text.clear()
//            qtyEditText.visibility = View.INVISIBLE
        }
    }
}