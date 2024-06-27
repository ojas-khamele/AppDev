package com.example.restaurantapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class AdminActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_activity_layout)

        val backBtn = findViewById<Button>(R.id.btnBack)

        backBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val insertBtn = findViewById<Button>(R.id.btnAddItems)

        insertBtn.setOnClickListener{
            val intent = Intent(this, AdminInsertItem::class.java)
            startActivity(intent)
        }

        val listBtn = findViewById<Button>(R.id.btnRemoveItems)

        listBtn.setOnClickListener{
            val intent = Intent(this, AdminRemoveItem::class.java)
            startActivity(intent)
        }
    }

}
