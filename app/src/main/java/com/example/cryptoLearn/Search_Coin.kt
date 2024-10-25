package com.example.cryptoLearn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Search_Coin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_coin)

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intentDashboard = Intent(this@Search_Coin, Wallet::class.java)
            startActivity(intentDashboard)
            finish()
        }
        val action = intent.getStringExtra("action")

        val selectCoinButton = findViewById<Button>(R.id.selectCoinButton)
        selectCoinButton.setOnClickListener {
            if (action == "buy") {
                val intentBuy = Intent(this@Search_Coin, Wallet_Buy::class.java)
                startActivity(intentBuy)
            } else if (action == "sell") {
                val intentSell = Intent(this@Search_Coin, Wallet_Sell::class.java)
                startActivity(intentSell)
            }
        }
    }
}
