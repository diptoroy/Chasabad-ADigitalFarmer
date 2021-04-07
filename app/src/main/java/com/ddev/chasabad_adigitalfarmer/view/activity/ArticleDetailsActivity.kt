package com.ddev.chasabad_adigitalfarmer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        val bundle:Bundle? = intent.extras
        article_details_title.text = bundle!!.getString("title")
        article_details_date.text = bundle!!.getString("date")
        article_details_author.text = bundle!!.getString("author")
        article_details_source.text = bundle!!.getString("source")
        val all: String? = bundle!!.getString("description") +
                bundle!!.getString("content") +
                bundle!!.getString("description") +
                bundle!!.getString("content") +
                bundle!!.getString("description") +
                bundle!!.getString("content") +
                bundle!!.getString("description") +
                bundle!!.getString("content")
        article_details_details.text = all
        Glide.with(this).load(bundle.getString("image")).into(articledetails_image);
    }
}