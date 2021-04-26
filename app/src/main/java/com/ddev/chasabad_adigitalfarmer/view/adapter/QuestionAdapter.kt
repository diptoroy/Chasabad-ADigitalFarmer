package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.question.QuestionData
import com.ddev.chasabad_adigitalfarmer.util.TimeConverter
import com.ddev.chasabad_adigitalfarmer.util.clickListener.QuestionOnClickListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.question_row.view.*

class QuestionAdapter(private val questionOnClickListener: QuestionOnClickListener) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    private var questionList = emptyList<QuestionData>()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val admin = "XAQT5Pbde7gRFE2AfqSUDN6eD0g1"
    private val currentUser: String = mAuth.currentUser.uid
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.question_text.text = questionList[position].question.toString()
        holder.itemView.answer_text.text = questionList[position].answer.toString()
        holder.itemView.user_text.text = questionList[position].userId.toString()
        holder.itemView.time_text.text = questionList[position].time?.let { TimeConverter.getTimeAgo(it) }
//        Glide.with(holder.itemView.context).load(questionList[position].image)
//            .into(holder.itemView.question_image)
//        holder.itemView.acotrName.text = [position].actorDetails?.get(position)?.actorName
//        com.bumptech.glide.Glide.with(holder.itemView.context).load([position].actorDetails?.get(position)?.actorImage).into(holder.itemView.actorImage)
//
//
        if (currentUser == admin) {

            holder.itemView.setOnClickListener {
                questionOnClickListener.onClick(questionList[position], position)
            }
        }else{

        }


    }


    override fun getItemCount(): Int {
        return questionList.size
    }

    fun setData(newList: List<QuestionData>) {
        notifyDataSetChanged()
        questionList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}