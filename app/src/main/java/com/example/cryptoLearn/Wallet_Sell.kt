package com.example.cryptoLearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class Wallet_Sell : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_sell)
        val backArrow = findViewById<ImageView>(R.id.back_arrow)
        backArrow.setOnClickListener {
            val intentDashboard = Intent(this@Wallet_Sell, Wallet::class.java)
            startActivity(intentDashboard)
            finish()
        }
        val exchangeValue = findViewById<EditText>(R.id.exchange_value)
        val coinInput = findViewById<EditText>(R.id.coin_input)

        val cancelBtn = findViewById<Button>(R.id.cancelBtn)

        cancelBtn.setOnClickListener {
            exchangeValue.text.clear()
            coinInput.text.clear()
        }
    }
}