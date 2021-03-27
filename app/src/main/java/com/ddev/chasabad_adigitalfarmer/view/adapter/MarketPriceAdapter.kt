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
        holder.itemView.productName.text = marketPriceList[position].productName
        holder.itemView.current_sell_price.text = marketPriceList[position].productCurrentSellPrice
        holder.itemView.current_buy_price.text = marketPriceList[position].productCurrentBuyPrice
        holder.itemView.previous_sell_price.text = marketPriceList[position].productPreviousSellPrice
        holder.itemView.previous_buy_price.text = marketPriceList[position].productPreviousBuyPrice

        setUpDown(holder, position)

    }

    override fun getItemCount(): Int {
        return marketPriceList.size
    }

    fun setData(newList: List<MarketPriceData>){
        notifyDataSetChanged()
         marketPriceList= newList
        notifyDataSetChanged()
    }

    private fun setUpDown(holder: ViewHolder, position: Int){
        when {
            marketPriceList[position].productCurrentSellPrice > marketPriceList[position].productPreviousSellPrice -> {
                holder.itemView.ts_signal.visibility = View.VISIBLE
                holder.itemView.ts_signal.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
            }
            marketPriceList[position].productCurrentSellPrice < marketPriceList[position].productPreviousSellPrice -> {
                holder.itemView.ts_signal.visibility = View.VISIBLE
                holder.itemView.ts_signal.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
            }
            else -> {
                holder.itemView.ts_signal.visibility = View.INVISIBLE
            }
        }

        when {
            marketPriceList[position].productCurrentBuyPrice > marketPriceList[position].productPreviousBuyPrice -> {
                holder.itemView.tb_signal.visibility = View.VISIBLE
                holder.itemView.tb_signal.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
            }
            marketPriceList[position].productCurrentBuyPrice < marketPriceList[position].productPreviousBuyPrice -> {
                holder.itemView.tb_signal.visibility = View.VISIBLE
                holder.itemView.tb_signal.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
            }
            else -> {
                holder.itemView.tb_signal.visibility = View.INVISIBLE
            }
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}

