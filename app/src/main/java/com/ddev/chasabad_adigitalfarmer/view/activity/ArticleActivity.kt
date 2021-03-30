package com.ddev.chasabad_adigitalfarmer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.news.NewsData
import com.ddev.chasabad_adigitalfarmer.repository.Repository
import com.ddev.chasabad_adigitalfarmer.util.Constants.Companion.NEWS_APP_ID
import com.ddev.chasabad_adigitalfarmer.util.OnItemClickListener
import com.ddev.chasabad_adigitalfarmer.view.adapter.ArticleAdapter
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.ArticleActivityViewModel
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.ArticleActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_news.*

class ArticleActivity : AppCompatActivity(),OnItemClickListener {

    private lateinit var viewModel: ArticleActivityViewModel
    private val articleAdapter by lazy { ArticleAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val repository = Repository()
        val viewModelFactory = ArticleActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArticleActivityViewModel::class.java)

        viewModel.getNews(NEWS_APP_ID)
        viewModel.newsResponse.observe(this, Observer { response ->
            if (response.isSuccessful){
                Log.d("article", response.body().toString())
                news_day.text = response.body()?.articles?.get(1)?.title
                Glide.with(this).load(response.body()?.articles?.get(1)?.urlToImage).into(news_day_image)
                //articleAdapter.setData(response)
            }else{
                Log.d("Response", response.errorBody().toString())
            }

            //        articleAdapter.setData(response)
//            Log.d("article",articleList.toString())
        })

        setUpNews()
    }

    private fun setUpNews() {
        article_recyclerview.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        article_recyclerview.setHasFixedSize(true)
        article_recyclerview.adapter = articleAdapter
    }

    override fun onClick(item: NewsData, position: Int) {

    }
}

