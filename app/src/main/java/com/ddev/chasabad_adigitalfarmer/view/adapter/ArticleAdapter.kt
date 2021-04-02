package com.ddev.chasabad_adigitalfarmer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.news.NewsData
import com.ddev.chasabad_adigitalfarmer.util.OnItemClickListener

class ArticleAdapter (private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private var articleList = emptyList<NewsData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemView.article_headline.text = articleList[position].articles[position].title
//        holder.itemView.article_author.text = articleList[position].articles[position].author
//        holder.itemView.article_date.text = articleList[position].articles[position].publishedAt
//        Glide.with(holder.itemView.context).load(articleList[position].articles[position].urlToImage).into(holder.itemView.article_image)
//
//        holder.itemView.setOnClickListener {
//            onItemClickListener.onClick(articleList[position],position)
//        }

    }
    override fun getItemCount(): Int {
        return articleList.size
    }

    fun setData(newList: List<NewsData>) {
        notifyDataSetChanged()
        articleList = newList
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}