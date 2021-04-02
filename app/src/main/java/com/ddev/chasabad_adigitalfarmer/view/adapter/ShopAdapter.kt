package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.shop.ShopData
import com.ddev.chasabad_adigitalfarmer.util.ShopOnItemClickListener
import kotlinx.android.synthetic.main.shop_row.view.*

class ShopAdapter(private val shopOnItemClickListener: ShopOnItemClickListener) : RecyclerView.Adapter<ShopAdapter.ViewHolder>() {
    private var shopList = emptyList<ShopData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.product_name.text = shopList[position].productName.toString()
        holder.itemView.product_price.text = shopList[position].productPrice.toString()
        holder.itemView.product_star.text = shopList[position].productStar.toString()
        com.bumptech.glide.Glide.with(holder.itemView.context).load(shopList[position].productImage).into(holder.itemView.product_image)

        holder.itemView.setOnClickListener{
           shopOnItemClickListener.onClick(shopList[position],position)
        }

    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    fun setData(newList: List<ShopData>) {
        notifyDataSetChanged()
        shopList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

