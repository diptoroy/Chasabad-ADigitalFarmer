package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.model.marketPrice.MarketPriceData
import kotlinx.android.synthetic.main.market_price_row.view.*
import java.util.logging.Filter

class MarketPriceAdapter : RecyclerView.Adapter<MarketPriceAdapter.ViewHolder>(),Filterable {
    private var marketPriceList = emptyList<MarketPriceData>()
    private var searchList = emptyList<MarketPriceData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.market_price_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.fertilizerName.text = marketPriceList[position].productName
        holder.itemView.current_sell_price.text = marketPriceList[position].productCurrentSellPrice
        holder.itemView.current_buy_price.text = marketPriceList[position].productCurrentBuyPrice
        holder.itemView.fertilizer_second.text = marketPriceList[position].productPreviousSellPrice
        holder.itemView.previous_buy_price.text = marketPriceList[position].productPreviousBuyPrice

        setUpDown(holder, position)

    }

    override fun getItemCount(): Int {
        return marketPriceList.size
    }

    fun setData(newList: List<MarketPriceData>) {
        notifyDataSetChanged()
        marketPriceList = newList
        this.searchList = newList
        notifyDataSetChanged()
    }

    private fun setUpDown(holder: ViewHolder, position: Int) {
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    @ExperimentalStdlibApi
    override fun getFilter(): android.widget.Filter {
        return object : android.widget.Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {

                var filterResult = FilterResults()
                if (charSequence == null || charSequence.length < 0) {
                    filterResult.count = searchList.size
                    filterResult.values = searchList
                } else {
                    var searchChar: String = charSequence.toString().toLowerCase()
                    val list = ArrayList<MarketPriceData>()
                    for (item in searchList){
                        if (item.productName.lowercase().contains(searchChar) || item.productName.lowercase().contains(searchChar)){
                            list.add(item)
                        }
                    }
                    filterResult.count = list.size
                    filterResult.values = list
                }
                return filterResult
            }


            override fun publishResults(c0: CharSequence?, results: FilterResults?) {
                marketPriceList = results!!.values as ArrayList<MarketPriceData>
                notifyDataSetChanged()
            }

        }
    }
}

