package com.example.cryptoLearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class Wallet_Buy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_buy)
        val backArrow = findViewById<ImageView>(R.id.back_arrow)
        backArrow.setOnClickListener {
            val intentDashboard = Intent(this@Wallet_Buy, Wallet::class.java)
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