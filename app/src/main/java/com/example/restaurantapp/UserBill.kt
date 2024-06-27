package com.example.restaurantapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class UserBill : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_bill_layout)

        billItems()
    }

    private fun billItems() {

        val linearLayout: LinearLayout = findViewById(R.id.linearLayout)
        var netAmount = 0.0

        OrderList.foodList.forEach { item ->
            val textView = TextView(this)
            textView.text = "Item : ${item.name}, Price - Rs${item.price} ,Qty - ${item.quantity}"
            textView.textSize = 18f
            textView.setPadding(16, 16, 16, 16)
            netAmount += item.price * item.quantity
            linearLayout.addView(textView)
        }

        val gst = String.format("%.2f", 0.05 * netAmount).toDouble()

        val tvTax = findViewById<TextView>(R.id.tvTax)
        val tvAmt = findViewById<TextView>(R.id.tvNetAmt)

        tvTax.setText("Amount = Rs${netAmount} + Rs${gst}(GST)")
        tvAmt.setText("Final - Rs${netAmount+gst}")

        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnBack = findViewById<Button>(R.id.btnBack7)

        btnClear.setOnClickListener {
            Toast.makeText(this, "Bill issued successfully..", Toast.LENGTH_SHORT).show()
            OrderList.foodList.clear()
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }
}