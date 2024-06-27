package com.example.restaurantapp

import android.content.ContentValues
import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminInsertItem : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_insertactivity_layout)

        dbHelper = DatabaseHelper(this)

        val backBtn = findViewById<Button>(R.id.btnBack2)

        backBtn.setOnClickListener{
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }

        val insertBtn = findViewById<Button>(R.id.btnInsert)

        insertBtn.setOnClickListener {

            val name = findViewById<EditText>(R.id.etName2)
            val price = findViewById<EditText>(R.id.etPrice)
            val category = findViewById<EditText>(R.id.etCategory)
            val description = findViewById<EditText>(R.id.etDescription)
            insertData(name.text.toString(), price.text.toString().toInt(), category.text.toString(), description.text.toString())
            Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }
    }

    private fun insertData(name:String, price: Int, category:String, description:String) {
        val db = dbHelper.writableDatabase

        val foodItemValues = ContentValues().apply{
            put(DatabaseHelper.COLUMN_ITEM_NAME, name)
            put(DatabaseHelper.COLUMN_ITEM_PRICE, price)
        }

        val foodItemDetails = ContentValues().apply{
            put(DatabaseHelper.COLUMN_ITEM_NAME, name)
            put(DatabaseHelper.COLUMN_CATEGORY, "Fast Food")
            put(DatabaseHelper.COLUMN_DESCRIPTION, "Cheesy!! Prep time = 15min")
        }

        db.insert(DatabaseHelper.TABLE_FOOD_ITEMS, null, foodItemValues)
        db.insert(DatabaseHelper.TABLE_FOOD_DETAILS, null, foodItemDetails)
    }
}