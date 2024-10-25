package com.example.cryptoLearn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoLearn.R
import com.example.cryptoLearn.databinding.TopCurrencyLayoutBinding
import com.example.cryptoLearn.fragment.HomeFragment
import com.example.cryptoLearn.fragment.HomeFragmentDirections
import com.example.cryptoLearn.models.CryptoCurrency

class TopMarketAdapter(
    private val context: Context,
    private val list: List<CryptoCurrency>
) : RecyclerView.Adapter<TopMarketAdapter.TopMarketViewHolder>() {

    inner class TopMarketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TopCurrencyLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMarketViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.top_currency_layout, parent, false)
        return TopMarketViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopMarketViewHolder, position: Int) {
        val item = list[position]
        holder.binding.topCurrencyNameTextView.text = item.name

        Glide.with(context)
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/${item.id}.png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.topCurrencyImageView)

        if (item.quotes!![0].percentChange24h > 0) {
            holder.binding.topCurrencyChangeTextView.setTextColor(
                ContextCompat.getColor(context, R.color.green)
            )
            holder.binding.topCurrencyChangeTextView.text = "+${String.format("%.02f",item.quotes[0].percentChange24h)} %"
        } else {
            holder.binding.topCurrencyChangeTextView.setTextColor(
                ContextCompat.getColor(context, R.color.red)
            )
            holder.binding.topCurrencyChangeTextView.text = "${String.format("%.02f",item.quotes[0].percentChange24h)} %"
        }

        holder.itemView.setOnClickListener{
            Navigation.findNavController(it).navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item)
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
