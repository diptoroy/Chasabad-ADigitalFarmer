package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.MenuData
import com.ddev.chasabad_adigitalfarmer.view.activity.*
import kotlinx.android.synthetic.main.menu_row.view.*


class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private var menuList = emptyList<MenuData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.menu_name.text = menuList[position].menuName.toString()
        Glide.with(holder.itemView.context).load(menuList[position].menuImage)
            .into(holder.itemView.menu_image).toString()
        holder.itemView.setOnClickListener {

            when (position) {
                0 -> {
                    Log.d("clicked!", position.toString())
                    val intent = Intent(holder.itemView.context, CropActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }1 -> {
                    Log.d("clicked!", position.toString())
                    val intent = Intent(holder.itemView.context, NurseryActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }2 -> {
                    Log.d("clicked!", position.toString())
                    val intent = Intent(holder.itemView.context, DiseaseActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }3 -> {
                    Log.d("clicked!", position.toString())
                    val intent = Intent(holder.itemView.context, MarketPriceActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }4 -> {
                    Log.d("clicked!", position.toString())
                    val intent = Intent(holder.itemView.context, ShopActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }5 -> {
                    Log.d("clicked!", position.toString())
                    val intent = Intent(holder.itemView.context, ArticleActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }6 -> {
                    Log.d("clicked!", position.toString())
                    val intent = Intent(holder.itemView.context, FarmingActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }7 -> {
                    Log.d("clicked!", position.toString())
                    val intent = Intent(holder.itemView.context, ArticleActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun setData(newList: List<MenuData>) {
        notifyDataSetChanged()
        menuList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}