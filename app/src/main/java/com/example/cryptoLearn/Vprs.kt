package com.example.cryptoLearn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cryptoLearn.databinding.ActivityVprsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Vprs : AppCompatActivity() {
    lateinit var binding: ActivityVprsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVprsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomBar.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        val intent = Intent(this, dashboard::class.java)
        intent.putExtra("navigateToHome", true)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }
}
