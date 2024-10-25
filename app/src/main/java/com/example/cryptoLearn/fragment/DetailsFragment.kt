package com.example.cryptoLearn.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cryptoLearn.R
import com.example.cryptoLearn.Vprs
import com.example.cryptoLearn.databinding.FragmentDetailsBinding
import com.example.cryptoLearn.models.CryptoCurrency
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Locale
import java.text.NumberFormat
import java.util.ArrayList


class DetailsFragment : Fragment() {

    lateinit var binding : FragmentDetailsBinding

    private val item : DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        val data : CryptoCurrency = item.data!!

        setUpDetails(data)
        loadChart(data)
        setButtonClick(data)
        setUpBackStackButton()
        addToWatchList(data);
        return binding.root
    }
    var watchList : ArrayList<String>? = null
    var watchListIsChecked = false
    private fun addToWatchList(data: CryptoCurrency) {
        readData()
        watchListIsChecked = if (watchList!!.contains(data.symbol)) {
            binding.addWatchlistButton.setImageResource(R.drawable.ic_star)
            true
        } else {
            binding.addWatchlistButton.setImageResource(R.drawable.ic_star_outline)
            false
        }
        binding.addWatchlistButton.setOnClickListener {
            watchListIsChecked =
                if (!watchListIsChecked) {
                    if (!watchList!!.contains(data.symbol)) {
                        watchList!!.add(data.symbol)
                    }
                    storeData()
                    binding.addWatchlistButton.setImageResource(R.drawable.ic_star)
                    true
                } else {
                    binding.addWatchlistButton.setImageResource(R.drawable.ic_star_outline)
                    watchList!!.remove(data.symbol)
                    storeData()
                    false
                }
        }
    }
    private fun storeData(){
        val sharedPreferences =  requireContext().getSharedPreferences("watchlist", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(watchList)
        editor.putString("watchlist", json)
        editor.apply()
    }

    private fun readData() {
        val sharedPreferences = requireContext().getSharedPreferences("watchlist", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("watchlist", ArrayList<String>().toString())
        val type = object : TypeToken<ArrayList<String>>(){}.type
        watchList = gson.fromJson(json, type)
    }

    override fun onResume() {
        super.onResume()
        // Hide the bottom navigation bar when the fragment is visible
        (activity as? Vprs)?.binding?.bottomBar?.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        // Show the bottom navigation bar when the fragment is not visible
        (activity as? Vprs)?.binding?.bottomBar?.visibility = View.VISIBLE
    }
    private fun setUpBackStackButton() {
        binding.backStackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setButtonClick(item: CryptoCurrency) {
        val oneMonth = binding.button
        val oneWeek = binding.button1
        val oneDay = binding.button2
        val fourHour = binding.button3
        val oneHour = binding.button4
        val fifteenMinute = binding.button5

        val clickListener = View.OnClickListener {
            when(it.id){
                fifteenMinute.id -> loadChartData(it, "15", item, oneDay, oneMonth, oneWeek, fourHour, oneHour)
                oneHour.id -> loadChartData(it, "1H", item, oneDay, oneMonth, oneWeek, fourHour, fifteenMinute)
                fourHour.id -> loadChartData(it, "4H", item, oneDay, oneMonth, oneWeek, fifteenMinute, oneHour)
                oneDay.id -> loadChartData(it, "D", item, fifteenMinute, oneMonth, oneWeek, fourHour, oneHour)
                oneWeek.id -> loadChartData(it, "W", item, oneDay, oneMonth, fifteenMinute, fourHour, oneHour)
                oneMonth.id -> loadChartData(it, "M", item, oneDay, fifteenMinute, oneWeek, fourHour, oneHour)
            }
        }

        fifteenMinute.setOnClickListener(clickListener)
        oneHour.setOnClickListener(clickListener)
        fourHour.setOnClickListener(clickListener)
        oneDay.setOnClickListener(clickListener)
        oneWeek.setOnClickListener(clickListener)
        oneMonth.setOnClickListener(clickListener)
    }

    private fun loadChartData(
        it: View?,
        s: String,
        item: CryptoCurrency,
        oneDay: AppCompatButton,
        oneMonth: AppCompatButton,
        oneWeek: AppCompatButton,
        fourHour: AppCompatButton,
        oneHour: AppCompatButton
    ) {
        disableButton(oneDay, oneMonth, oneWeek, fourHour, oneHour)
        it!!.setBackgroundResource(R.drawable.active_button)
        (it as AppCompatButton).setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        binding.detaillChartWebView.apply {
            settings.javaScriptEnabled = true
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)

            val url = "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87" +
                    "&symbol=${item.symbol}USD" +
                    "&interval=$s" +
                    "&hide_side_toolbar=1" +  // Hide side toolbar
                    "&hide_top_toolbar=1" +   // Hide top toolbar
                    "&hideideas=1" +        // Hide ideas
                    "&hide_legend=1" +      // Hide the legend
                    "&theme=Light" +
                    "&style=1" +
                    "&timezone=Etc%2FUTC" +
                    // Add RSI, two EMAs, MACD, and Pivot Points for support and resistance
                    "&studies=[\"RSI@tv-basicstudies\",\"EMA@tv-basicstudies\",\"EMA@tv-basicstudies\",\"MACD@tv-basicstudies\",\"PivotPointsHighLow@tv-basicstudies\"]" +
                    "&studies_overrides={\"RSI@tv-basicstudies.Length\":14," +
                    "\"EMA@tv-basicstudies.0.Length\":9,\"EMA@tv-basicstudies.0.color\":\"#FF0000\"," + // Customize 9 EMA (red)
                    "\"EMA@tv-basicstudies.1.Length\":21,\"EMA@tv-basicstudies.1.color\":\"#0000FF\"," +  // Customize 21 EMA (blue)
                    "\"MACD@tv-basicstudies.fastLength\":12,\"MACD@tv-basicstudies.slowLength\":26,\"MACD@tv-basicstudies.signalLength\":9}" + // Customize MACD
                    "&overrides={}" +
                    "&enabled_features=[]" +
                    "&disabled_features=[]" +
                    "&locale=en" +
                    "&utm_source=coinmarketcap.com" +
                    "&utm_medium=widget" +
                    "&utm_campaign=chart" +
                    "&utm_term=${item.symbol}USD"

            loadUrl(url)
        }
    }

    private fun disableButton(oneDay: AppCompatButton, oneMonth: AppCompatButton, oneWeek: AppCompatButton, fourHour: AppCompatButton, oneHour: AppCompatButton) {
        oneDay.background = null
        oneMonth.background = null
        oneWeek.background = null
        fourHour.background = null
        oneHour.background = null

        oneDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.btn))
        oneMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.btn))
        oneWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.btn))
        fourHour.setTextColor(ContextCompat.getColor(requireContext(), R.color.btn))
        oneHour.setTextColor(ContextCompat.getColor(requireContext(), R.color.btn))

    }

    private fun loadChart(item: CryptoCurrency) {
        binding.detaillChartWebView.apply {
            settings.javaScriptEnabled = true
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)

            val url = "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87" +
                    "&symbol=${item.symbol}USD" +
                    "&interval=D" +
                    "&hide_side_toolbar=1" +  // Hide side toolbar
                    "&hide_top_toolbar=1" +   // Hide top toolbar
                    "&hideideas=1" +        // Hide ideas
                    "&hide_legend=1" +      // Hide the legend
                    "&theme=Light" +
                    "&style=1" +
                    "&timezone=Etc%2FUTC" +
                    // Add RSI, two EMAs, MACD, and Pivot Points for support and resistance
                    "&studies=[\"RSI@tv-basicstudies\",\"EMA@tv-basicstudies\",\"EMA@tv-basicstudies\",\"MACD@tv-basicstudies\",\"PivotPointsHighLow@tv-basicstudies\"]" +
                    "&studies_overrides={\"RSI@tv-basicstudies.Length\":14," +
                    "\"EMA@tv-basicstudies.0.Length\":9,\"EMA@tv-basicstudies.0.color\":\"#FF0000\"," + // Customize 9 EMA (red)
                    "\"EMA@tv-basicstudies.1.Length\":21,\"EMA@tv-basicstudies.1.color\":\"#0000FF\"," +  // Customize 21 EMA (blue)
                    "\"MACD@tv-basicstudies.fastLength\":12,\"MACD@tv-basicstudies.slowLength\":26,\"MACD@tv-basicstudies.signalLength\":9}" + // Customize MACD
                    "&overrides={}" +
                    "&enabled_features=[]" +
                    "&disabled_features=[]" +
                    "&locale=en" +
                    "&utm_source=coinmarketcap.com" +
                    "&utm_medium=widget" +
                    "&utm_campaign=chart" +
                    "&utm_term=${item.symbol}USD"

            loadUrl(url)
        }
    }

    private fun setUpDetails(data: CryptoCurrency) {
        binding.detailSymbolTextView.text = data.symbol

        Glide.with(requireContext())
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/${data.id}.png")
            .thumbnail(Glide.with(requireContext()).load(R.drawable.spinner))
            .into(binding.detailImageView)

        val price = data.quotes[0].price
        binding.detailPriceTextView.text =
            if (price != null) {
                "${String.format("$%.4f", price)} "
            } else {
                "N/A"
            }

        if (data.quotes!![0].percentChange24h > 0) {
            binding.detailChangeTextView.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.green)
            )
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_up)
            binding.detailChangeTextView.text = "+${String.format("%.02f",data.quotes[0].percentChange24h)} %"
        } else {
            binding.detailChangeTextView.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.red)
            )
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_down)
            binding.detailChangeTextView.text = "${String.format("%.02f",data.quotes[0].percentChange24h)} %"
        }
        // Format the supply values as whole numbers with commas
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        val circulatingSupplyFormatted = numberFormat.format(data.circulatingSupply.toLong())
        val totalSupplyFormatted = numberFormat.format(data.totalSupply.toLong())
        val maxSupplyFormatted = numberFormat.format(data.maxSupply.toLong())

        // Populate the new TextViews
        binding.circulatingSupplyTextView.text = "Circulating Supply: $circulatingSupplyFormatted"
        binding.totalSupplyTextView.text = "Total Supply: $totalSupplyFormatted"
        binding.maxSupplyTextView.text = "Max Supply: $maxSupplyFormatted"
        binding.tagsTextView.text = "Tags: ${data.tags.joinToString(", ")}"
    }

}