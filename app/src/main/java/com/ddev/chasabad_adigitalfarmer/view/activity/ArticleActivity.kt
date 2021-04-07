package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.news.Article
import com.ddev.chasabad_adigitalfarmer.model.news.NewsData
import com.ddev.chasabad_adigitalfarmer.repository.Repository
import com.ddev.chasabad_adigitalfarmer.util.ArticleOnItemClickListener
import com.ddev.chasabad_adigitalfarmer.util.Constants.Companion.NEWS_APP_ID
import com.ddev.chasabad_adigitalfarmer.view.adapter.ArticleAdapter
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.ArticleActivityViewModel
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.ArticleActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_news.*

class ArticleActivity : AppCompatActivity(),ArticleOnItemClickListener {

    private lateinit var viewModel: ArticleActivityViewModel
    private val articleAdapter by lazy { ArticleAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val repository = Repository()
        val viewModelFactory = ArticleActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArticleActivityViewModel::class.java)

//        viewModel.getNews(NEWS_APP_ID)
//        viewModel.newsResponse.observe(this, Observer { response ->
//            if (response.isSuccessful){
//               Log.d("article", response.body().toString())
////                news_day.text = response.body()?.articles?.get(1)?.title
////                Glide.with(this).load(response.body()?.articles?.get(1)?.urlToImage).into(news_day_image)
//                //response.body()?.let { articleAdapter.setData(it)}
//                //articleAdapter.setData(response.body())
//
//            }else{
//                Log.d("Response", response.errorBody().toString())
//            }
//        })

        viewModel.getFakeNews()
        viewModel.newsFakeResponse.observe(this, Observer { response ->
            articleAdapter.setData(response)
            news_day.text = response[1].title
            Glide.with(this).load(response[1].urlToImage)
                .into(news_day_image)
            Log.d("Response", response.toString())
        })

        viewModel.getAllCrops()
        viewModel.cropResponse.observe(this, Observer { response ->
            Log.d("cropdata", response.body().toString())
        })

        setUpNews()
    }

    private fun setUpNews() {
        article_recyclerview.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        article_recyclerview.setHasFixedSize(true)
        article_recyclerview.adapter = articleAdapter
    }

    override fun onClick(item: Article, position: Int) {
        Toast.makeText(applicationContext, "Cliked$position",Toast.LENGTH_SHORT).show()
        val intent = Intent(this,ArticleDetailsActivity::class.java)
        intent.putExtra("image",item.urlToImage)
        intent.putExtra("author",item.author)
        intent.putExtra("title",item.title)
        intent.putExtra("date",item.publishedAt)
        //intent.putExtra("source",item.source)
        intent.putExtra("description",item.description)
        intent.putExtra("content",item.content)
        startActivity(intent)
    }


}

