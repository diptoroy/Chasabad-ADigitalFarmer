package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.question.QuestionData
import com.ddev.chasabad_adigitalfarmer.util.TimeConverter
import kotlinx.android.synthetic.main.question_row.view.*

class ProfileQuestionAdapter : RecyclerView.Adapter<ProfileQuestionAdapter.ViewHolder>() {
    private var pQuestionList = emptyList<QuestionData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.question_text.text = pQuestionList[position].question.toString()
        holder.itemView.answer_text.text = pQuestionList[position].answer.toString()
        holder.itemView.time_text.text = pQuestionList[position].time?.let { TimeConverter.getTimeAgo(it) }
//        holder.itemView.movieName.text = [position].title.toString()
//        com.bumptech.glide.Glide.with(holder.itemView.context).load([position].poster).into(holder.itemView.moviePoster)
//        holder.itemView.acotrName.text = [position].actorDetails?.get(position)?.actorName
//        com.bumptech.glide.Glide.with(holder.itemView.context).load([position].actorDetails?.get(position)?.actorImage).into(holder.itemView.actorImage)
//
//        holder.itemView.setOnClickListener{
//            val intent = Intent(holder.itemView.context, MovieDetailsActivity::class.java)
//            intent.putExtra("title",[position].title)
//            intent.putExtra("director",[position].director)
//            holder.itemView.context.startActivity(intent)
//        }

    }

    override fun getItemCount(): Int {
        return pQuestionList.size
    }

    fun setData(newList: List<QuestionData>) {
        notifyDataSetChanged()
        pQuestionList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}