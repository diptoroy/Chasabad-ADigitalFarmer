package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.event.EventData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.EventOnClickListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.event_row.view.*

class EventAdapter() : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    private var eventList = emptyList<EventData>()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val currentUser: String = mAuth.currentUser.uid

    private val admin = "XAQT5Pbde7gRFE2AfqSUDN6eD0g1"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.event_name.text = eventList[position].eventName
        holder.itemView.event_location.text = eventList[position].eventLocation
        holder.itemView.event_date.text = eventList[position].eventDate
        holder.itemView.event_month.text = eventList[position].eventMonth

//        if (currentUser == admin) {
//
//            holder.itemView.setOnClickListener {
//                eventOnClickListener.onClick(eventList[position],position)
//            }
//        }else{
//
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