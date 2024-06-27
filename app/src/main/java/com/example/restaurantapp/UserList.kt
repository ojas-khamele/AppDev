package com.example.restaurantapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserList : AppCompatActivity() {
    private lateinit var dbHelper : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_list_layout)

        dbHelper = DatabaseHelper(this)

        val backBtn = findViewById<Button>(R.id.btnBack6)

        backBtn.setOnClickListener{
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        showList()
    }

    private fun showList() {

        val linearLayout: LinearLayout = findViewById(R.id.linearLayout)

        OrderList.foodList.forEach { item ->
            val textView = TextView(this)
            textView.text = "Item : ${item.name}, Qty - ${item.quantity}"
            textView.textSize = 18f
            textView.setPadding(16, 16, 16, 16)
            textView.setOnClickListener {
                removeItem(item)
            }

            linearLayout.addView(textView)
        }
    }

    private fun removeItem(foodItem: FoodItem) {
        Toast.makeText(this, "Press Delete to confirm", Toast.LENGTH_SHORT).show()
        val btn = findViewById<Button>(R.id.btnDelete2)
        btn.visibility = View.VISIBLE

        btn.setOnClickListener {
            OrderList.foodList.remove(foodItem)
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show()
            val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
            linearLayout.removeAllViews()
            showList()
//            btn.visibility = View.INVISIBLE
        }
    }
}