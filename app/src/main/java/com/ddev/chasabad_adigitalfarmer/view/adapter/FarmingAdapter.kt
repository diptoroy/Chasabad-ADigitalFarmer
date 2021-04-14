package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.farming.FarmingData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.FarmingOnItemClickListener
import kotlinx.android.synthetic.main.activity_farming.view.*
import kotlinx.android.synthetic.main.farming_row.view.*

class FarmingAdapter(private val farmingOnItemClickListener: FarmingOnItemClickListener) : RecyclerView.Adapter<FarmingAdapter.ViewHolder>() {
    private var farmingList = emptyList<FarmingData>()
    var mColors: Array<String> = arrayOf("#3F51B5","#FF9800","#009688","#673AB7")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.itemView.farming_layout.setBackgroundColor(Color.parseColor(mColors[position % 4]))
        holder.itemView.farming_name.text = farmingList[position].farmingName.toString()
        Glide.with(holder.itemView.context).load(farmingList[position].farmingImage).into(holder.itemView.farming_image)

        holder.itemView.setOnClickListener{
            farmingOnItemClickListener.onClick(farmingList[position],position)
        }

    }

    override fun getItemCount(): Int {
        return farmingList.size
    }

    fun setData(newList: List<FarmingData>) {
        notifyDataSetChanged()
        farmingList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}