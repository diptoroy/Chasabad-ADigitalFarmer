package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.model.nursery.NurseryData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.NurseryOnItemClickListener
import kotlinx.android.synthetic.main.nursery_row.view.*

class NurseryAdapter(private val nurseryOnItemClickListener: NurseryOnItemClickListener) :
    RecyclerView.Adapter<NurseryAdapter.ViewHolder>(),Filterable {
    private var nurseryList = emptyList<NurseryData>()
    private var searchList = emptyList<NurseryData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nursery_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.nursery_name.text = nurseryList[position].nurseryName.toString()
        com.bumptech.glide.Glide.with(holder.itemView.context)
            .load(nurseryList[position].nurseryImage).into(holder.itemView.nursey_image)

        holder.itemView.setOnClickListener {
            nurseryOnItemClickListener.onClick(nurseryList[position], position)
        }

    }

    override fun getItemCount(): Int {
        return nurseryList.size
    }

    fun setData(newList: List<NurseryData>) {
        notifyDataSetChanged()
        nurseryList = newList
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
                    val list = ArrayList<NurseryData>()
                    for (item in searchList){
                        if (item.nurseryName.lowercase().contains(searchChar) || item.nurseryOrigin.lowercase().contains(searchChar)){
                            list.add(item)
                        }
                    }
                    filterResult.count = list.size
                    filterResult.values = list
                }
                return filterResult
            }


            override fun publishResults(c0: CharSequence?, results: FilterResults?) {
                nurseryList = results!!.values as ArrayList<NurseryData>
                notifyDataSetChanged()
            }

        }
    }
}