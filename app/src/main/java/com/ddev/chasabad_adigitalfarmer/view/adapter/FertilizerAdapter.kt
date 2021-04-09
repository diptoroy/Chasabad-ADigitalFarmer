package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.model.crop.CropFertilizer
import kotlinx.android.synthetic.main.fertilizer_row.view.*

class FertilizerAdapter : RecyclerView.Adapter<FertilizerAdapter.ViewHolder>() {
    private var fertilizerList = emptyList<CropData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fertilizer_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.fertilizerName.text =
            fertilizerList[position].cropFertilizer[position].fertilizerName
        holder.itemView.fertilizer_first.text =
            fertilizerList[position].cropFertilizer[position].fertilizerFirst
        holder.itemView.fertilizer_second.text =
            fertilizerList[position].cropFertilizer[position].fertilizerSecond
        holder.itemView.fertilizer_third.text =
            fertilizerList[position].cropFertilizer[position].fertilizerThird
    }

    override fun getItemCount(): Int {
        return fertilizerList.size
    }

    fun setData(newList: List<CropData>) {
        notifyDataSetChanged()
        fertilizerList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}