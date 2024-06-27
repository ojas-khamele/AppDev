package com.example.restaurantapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminRemoveItem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_removeitem_layout)

        val backBtn = findViewById<Button>(R.id.btnBack5)

        backBtn.setOnClickListener{
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }

        val dbHelper = DatabaseHelper(this)

        val deleteBtn = findViewById<Button>(R.id.btnDelete)

        deleteBtn.setOnClickListener {

            val name = findViewById<EditText>(R.id.etName)
            val itemToDelete = name.text.toString()
            val deletedSuccessfully = dbHelper.deleteItem(itemToDelete)

            if (deletedSuccessfully) {
                Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
            
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }
    }
}