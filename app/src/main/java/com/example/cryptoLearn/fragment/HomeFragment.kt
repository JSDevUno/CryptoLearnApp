package com.example.cryptoLearn.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.cryptoLearn.adapter.TopLossGainPagerAdapter
import com.example.cryptoLearn.adapter.TopMarketAdapter
import com.example.cryptoLearn.apis.ApiInterface
import com.example.cryptoLearn.apis.ApiUtilities
import com.example.cryptoLearn.dashboard
import com.example.cryptoLearn.databinding.FragmentHome3Binding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHome3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHome3Binding.inflate(inflater, container, false)

        binding.topCurrencyRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.backArrow.setOnClickListener {
            val intent = Intent(activity, dashboard::class.java)
            startActivity(intent)
            activity?.finish()
        }

        getTopCurrencyList()

        setTabLayout()

        return binding.root
    }

    //LAYOUT TAB
    private fun setTabLayout() {
        val adapter = TopLossGainPagerAdapter(this)
        binding.contentViewPager.adapter = adapter

        binding.contentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.topGainIndicator.visibility = VISIBLE
                    binding.topLoseIndicator.visibility = GONE
                } else {
                    binding.topGainIndicator.visibility = GONE
                    binding.topLoseIndicator.visibility = VISIBLE
                }
            }
        })


        TabLayoutMediator(binding.tabLayout, binding.contentViewPager){
            tab, position ->
            var title = if (position == 0){
                "Top Gainers"
            }else{
                "Top Losers"
            }
            tab.text = title
        }.attach()

    }

    private fun getTopCurrencyList() {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()
                }

                if (response.isSuccessful) {
                    val marketData = response.body()?.data?.cryptoCurrencyList
                    Log.d("SHUSH", "getTopCurrencyList: $marketData")

                    withContext(Dispatchers.Main) {
                        // Pass the correct list to the adapter
                        binding.topCurrencyRecyclerView.adapter = TopMarketAdapter(
                            requireContext(),
                            marketData ?: emptyList() // Handle null case with emptyList
                        )
                    }
                } else {
                    Log.e("SHUSH", "Response error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("SHUSH", "Exception: ${e.message}")
            }
        }
    }
}
