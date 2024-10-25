package com.example.cryptoLearn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoLearn.R
import com.example.cryptoLearn.databinding.CurrencyItemLayoutBinding
import com.example.cryptoLearn.fragment.HomeFragmentDirections
import com.example.cryptoLearn.fragment.MarketFragmentDirections
import com.example.cryptoLearn.fragment.WatchListFragmentDirections
import com.example.cryptoLearn.models.CryptoCurrency

class MarketAdapter(var context: Context, var list: List<CryptoCurrency>, var type: String) : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    inner class MarketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       var binding = CurrencyItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        return MarketViewHolder(LayoutInflater.from(context).inflate(R.layout.currency_item_layout,parent,false))
    }

    fun updateData(dataItem : List<CryptoCurrency>){
        list= dataItem
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val item = list[position]
        holder.binding.currencyNameTextView.text =  item.name
        holder.binding.currencySymbolTextView.text =  item.symbol
        Glide.with(context)
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/${item.id}.png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.currencyImageView)
        Glide.with(context)
            .load("https://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/${item.id}.png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.currencyChartImageView)

        val price = item.quotes[0].price
        holder.binding.currencyPriceTextView.text =
            if (price != null) {
                "${String.format("$%.4f", price)} "
            } else {
                "N/A"
            }


        if (item.quotes!![0].percentChange24h > 0) {
            holder.binding.currencyChangeTextView.setTextColor(
                ContextCompat.getColor(context, R.color.green)
            )
            holder.binding.currencyChangeTextView.text = "+${String.format("%.02f",item.quotes[0].percentChange24h)} %"
        } else {
            holder.binding.currencyChangeTextView.setTextColor(
                ContextCompat.getColor(context, R.color.red)
            )
            holder.binding.currencyChangeTextView.text = "${String.format("%.02f",item.quotes[0].percentChange24h)} %"
        }

        holder.itemView.setOnClickListener{

            if(type == "home") {
                findNavController(it).navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item)
                )
            }else if(type == "market"){
                findNavController(it).navigate(
                    MarketFragmentDirections.actionMarketFragmentToDetailsFragment(item)
                )
            }else{
                findNavController(it).navigate(
                    WatchListFragmentDirections.actionWatchListFragmentToDetailsFragment(item)
                )
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
