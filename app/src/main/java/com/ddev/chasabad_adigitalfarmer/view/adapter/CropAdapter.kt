package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.CropOnItemClickListener
import kotlinx.android.synthetic.main.crop_row.view.*

class CropAdapter(private val cropOnItemClickListener: CropOnItemClickListener) :
    RecyclerView.Adapter<CropAdapter.ViewHolder>(), Filterable {
    private var cropList = emptyList<CropData>()
    private var searchList = emptyList<CropData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crop_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.crop_name.text = cropList[position].cropName
        com.bumptech.glide.Glide.with(holder.itemView.context).load(cropList[position].cropImage)
            .into(holder.itemView.crop_image)
        holder.itemView.crop_details.text = cropList[position].cropDetails
        holder.itemView.crop_time.text = cropList[position].cropTime

        holder.itemView.setOnClickListener {
            cropOnItemClickListener.onClick(cropList[position], position)
        }

    }

    override fun getItemCount(): Int {
        return cropList.size
    }

    fun setData(newList: List<CropData>) {
        notifyDataSetChanged()
        this.cropList = newList
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
                    val list = ArrayList<CropData>()
                    for (item in searchList){
                        if (item.cropName.lowercase().contains(searchChar) || item.cropType.lowercase().contains(searchChar)){
                            list.add(item)
                        }
                    }
                    filterResult.count = list.size
                    filterResult.values = list
                }
                return filterResult
            }


            override fun publishResults(c0: CharSequence?, results: FilterResults?) {
                cropList = results!!.values as ArrayList<CropData>
                notifyDataSetChanged()
            }

        }
    }
}