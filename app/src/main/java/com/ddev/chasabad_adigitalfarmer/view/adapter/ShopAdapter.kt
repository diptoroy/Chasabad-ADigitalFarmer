package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.model.shop.ShopData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.ShopOnItemClickListener
import kotlinx.android.synthetic.main.shop_row.view.*

class ShopAdapter(private val shopOnItemClickListener: ShopOnItemClickListener) :
    RecyclerView.Adapter<ShopAdapter.ViewHolder>(), Filterable {
    private var shopList = emptyList<ShopData>()
    private var searchList = emptyList<ShopData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.product_name.text = shopList[position].productName.toString()
        holder.itemView.product_price.text = shopList[position].productPrice.toString()
        holder.itemView.product_star.text = shopList[position].productStar.toString()
        com.bumptech.glide.Glide.with(holder.itemView.context).load(shopList[position].productImage)
            .into(holder.itemView.product_image)

        holder.itemView.setOnClickListener {
            shopOnItemClickListener.onClick(shopList[position], position)
        }

    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    fun setData(newList: List<ShopData>) {
        notifyDataSetChanged()
        shopList = newList
        this.searchList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    @ExperimentalStdlibApi
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {

                var filterResult = FilterResults()
                if (charSequence == null || charSequence.length < 0) {
                    filterResult.count = searchList.size
                    filterResult.values = searchList
                } else {
                    var searchChar: String = charSequence.toString().toLowerCase()
                    val list = ArrayList<ShopData>()
                    for (item in searchList) {
                        if (item.productName.lowercase()
                                .contains(searchChar) || item.productPrice.lowercase()
                                .contains(searchChar)
                        ) {
                            list.add(item)
                        }
                    }
                    filterResult.count = list.size
                    filterResult.values = list
                }
                return filterResult
            }


            override fun publishResults(c0: CharSequence?, results: FilterResults?) {
                shopList = results!!.values as ArrayList<ShopData>
                notifyDataSetChanged()
            }

        }
    }
}

