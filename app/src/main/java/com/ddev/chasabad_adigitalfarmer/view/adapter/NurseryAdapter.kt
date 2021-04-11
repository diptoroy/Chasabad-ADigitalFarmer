package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.nursery.NurseryData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.NurseryOnItemClickListener
import kotlinx.android.synthetic.main.nursery_row.view.*

class NurseryAdapter(private val nurseryOnItemClickListener: NurseryOnItemClickListener) : RecyclerView.Adapter<NurseryAdapter.ViewHolder>() {
    private var nurseryList = emptyList<NurseryData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nursery_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.nursery_name.text = nurseryList[position].nurseryName.toString()
        com.bumptech.glide.Glide.with(holder.itemView.context).load(nurseryList[position].nurseryImage).into(holder.itemView.nursey_image)

        holder.itemView.setOnClickListener{
            nurseryOnItemClickListener.onClick(nurseryList[position],position)
        }

    }

    override fun getItemCount(): Int {
        return nurseryList.size
    }

    fun setData(newList: List<NurseryData>){
        notifyDataSetChanged()
        nurseryList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}