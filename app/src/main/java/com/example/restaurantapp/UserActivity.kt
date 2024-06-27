package com.example.restaurantapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity(){
    private lateinit var dbHelper : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity_layout)

        dbHelper = DatabaseHelper(this)

        val backBtn = findViewById<Button>(R.id.btnBack3)

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btn = findViewById<Button>(R.id.btnSeeList)

        btn.setOnClickListener {
            val intent = Intent(this, UserList::class.java)
            startActivity(intent)
        }

        val orderBtn = findViewById<Button>(R.id.btnAddOrder)

        orderBtn.setOnClickListener {
            val intent = Intent(this, UserOrder::class.java)
            startActivity(intent)
        }

        val billBtn = findViewById<Button>(R.id.btnBill)

        billBtn.setOnClickListener {
            val intent = Intent(this, UserBill::class.java)
            startActivity(intent)
        }
    }
}