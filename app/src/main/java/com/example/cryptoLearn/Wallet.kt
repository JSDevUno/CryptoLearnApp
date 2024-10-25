package com.example.cryptoLearn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Wallet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        val backArrow = findViewById<ImageView>(R.id.back_arrow)
        backArrow.setOnClickListener {
            val intentDashboard = Intent(this@Wallet, dashboard::class.java)
            startActivity(intentDashboard)
            finish()
        }
        val buyBtn = findViewById<Button>(R.id.buyBtn)
        buyBtn.setOnClickListener {
            val intentBuy = Intent(this@Wallet, Search_Coin::class.java)
            intentBuy.putExtra("action", "buy")
            startActivity(intentBuy)
        }
        val sellBtn = findViewById<Button>(R.id.sellBtn)
        sellBtn.setOnClickListener {
            val intentSell = Intent(this@Wallet, Search_Coin::class.java)
            intentSell.putExtra("action", "sell")
            startActivity(intentSell)
        }
    }
    override fun onBackPressed() {
        val intent = Intent(this, dashboard::class.java)
        intent.putExtra("navigateToHome", true)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }
}