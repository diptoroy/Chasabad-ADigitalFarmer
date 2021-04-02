package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.util.CropOnItemClickListener
import kotlinx.android.synthetic.main.crop_row.view.*

class CropAdapter(private val cropOnItemClickListener: CropOnItemClickListener) : RecyclerView.Adapter<CropAdapter.ViewHolder>() {
    private var cropList = emptyList<CropData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crop_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.crop_name.text = cropList[position].cropName
        com.bumptech.glide.Glide.with(holder.itemView.context).load(cropList[position].cropImage).into(holder.itemView.crop_image)
        holder.itemView.crop_details.text = cropList[position].cropDetails
        holder.itemView.crop_time.text = cropList[position].cropTime
//        com.bumptech.glide.Glide.with(holder.itemView.context).load(cropList[position].actorDetails?.get(position)?.actorImage).into(holder.itemView.actorImage)
//
        holder.itemView.setOnClickListener{
            cropOnItemClickListener.onClick(cropList[position],position)
        }

    }

    override fun getItemCount(): Int {
        return cropList.size
    }

    fun setData(newList: List<CropData>){
        notifyDataSetChanged()
         cropList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}