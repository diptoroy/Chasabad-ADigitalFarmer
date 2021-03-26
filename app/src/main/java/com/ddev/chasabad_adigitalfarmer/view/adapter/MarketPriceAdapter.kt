package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.marketPrice.MarketPriceData
import kotlinx.android.synthetic.main.market_price_row.view.*
import kotlinx.android.synthetic.main.menu_row.view.*

class MarketPriceAdapter : RecyclerView.Adapter<MarketPriceAdapter.ViewHolder>() {
    private var marketPriceList = emptyList<MarketPriceData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.market_price_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.productName.text = marketPriceList[position].productName.toString()
        holder.itemView.current_sell_price.text = marketPriceList[position].productCurrentSellPrice.toString()
        holder.itemView.current_buy_price.text = marketPriceList[position].productCurrentBuyPrice.toString()
        holder.itemView.previous_sell_price.text = marketPriceList[position].productPreviousSellPrice.toString()
        holder.itemView.previous_buy_price.text = marketPriceList[position].productPreviousBuyPrice.toString()

//        if (marketPriceList[position].productCurrentSellPrice > marketPriceList[position].productPreviousSellPrice){
//            holder.itemView.ts_signal.visibility = View.INVISIBLE
//            Glide.with(holder.itemView.context).load(marketPriceList[position].pr)
//                .into(holder.itemView.ts_signal).toString()
//        }
    }

    override fun getItemCount(): Int {
        return marketPriceList.size
    }

    fun setData(newList: List<MarketPriceData>){
        notifyDataSetChanged()
         marketPriceList= newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}

