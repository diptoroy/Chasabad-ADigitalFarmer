package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.disease.DiseaseData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.DiseaseOnItemClickListener
import kotlinx.android.synthetic.main.disease_row.view.*

class DiseaseAdapter(private val diseaseOnItemClickListener: DiseaseOnItemClickListener) : RecyclerView.Adapter<DiseaseAdapter.ViewHolder>() {
    private var diseaseList = emptyList<DiseaseData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.disease_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.disease_name.text = diseaseList[position].diseaseName
        Glide.with(holder.itemView.context).load(diseaseList[position].diseaseImage).into(holder.itemView.disease_image).toString()
        holder.itemView.disease_details.text = diseaseList[position].diseaseDetails

        holder.itemView.setOnClickListener{
            diseaseOnItemClickListener.onClick(diseaseList[position],position)
        }

    }

    override fun getItemCount(): Int {
        return diseaseList.size
    }

    fun setData(newList: List<DiseaseData>){
        notifyDataSetChanged()
        diseaseList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}
