package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.event.EventData
import kotlinx.android.synthetic.main.event_row.view.*

class EventAdapter : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    private var eventList = emptyList<EventData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.event_name.text = eventList[position].eventName
        holder.itemView.event_location.text = eventList[position].eventLocation
        holder.itemView.event_date.text = eventList[position].eventDate
        holder.itemView.event_month.text = eventList[position].eventMonth
        Glide.with(holder.itemView.context).load(eventList[position].eventImage).into(holder.itemView.eventImage)

//        holder.itemView.setOnClickListener{
//            val intent = Intent(holder.itemView.context, MovieDetailsActivity::class.java)
//            intent.putExtra("title",[position].title)
//            intent.putExtra("director",[position].director)
//            holder.itemView.context.startActivity(intent)
//        }

    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    fun setData(newList: List<EventData>){
        notifyDataSetChanged()
         eventList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}