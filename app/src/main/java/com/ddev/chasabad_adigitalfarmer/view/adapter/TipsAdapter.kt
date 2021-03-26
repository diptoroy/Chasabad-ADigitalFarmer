package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.tips.TipsData
import kotlinx.android.synthetic.main.tips_row.view.*

class TipsAdapter : RecyclerView.Adapter<TipsAdapter.ViewHolder>() {
    private var tipsList  = emptyList<TipsData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tips_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tips_title.text = tipsList[position].title
        holder.itemView.tips_details.text = tipsList[position].details

//        holder.itemView.setOnClickListener{
//            val intent = Intent(holder.itemView.context, MovieDetailsActivity::class.java)
//            intent.putExtra("title",[position].title)
//            intent.putExtra("director",[position].director)
//            holder.itemView.context.startActivity(intent)
//        }

    }

    override fun getItemCount(): Int {
        return tipsList.size
    }

    fun setData(newList: List<TipsData>){
        notifyDataSetChanged()
         tipsList= newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}