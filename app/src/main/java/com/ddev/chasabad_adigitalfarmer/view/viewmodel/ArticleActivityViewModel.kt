package com.ddev.chasabad_adigitalfarmer.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddev.chasabad_adigitalfarmer.model.allCrop.AllCropData
import com.ddev.chasabad_adigitalfarmer.model.news.Article
import com.ddev.chasabad_adigitalfarmer.model.news.NewsData
import com.ddev.chasabad_adigitalfarmer.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ArticleActivityViewModel(private val repository: Repository): ViewModel() {
    val newsResponse: MutableLiveData<Response<NewsData>> = MutableLiveData()
    val newsFakeResponse: MutableLiveData<List<Article>> = MutableLiveData()
    val cropResponse: MutableLiveData<Response<AllCropData>> = MutableLiveData()

    fun getNews(apiKey: String){
        viewModelScope.launch {
            val n = repository.getNews(apiKey)
            newsResponse.value = n
        }
    }

    fun getFakeNews(){
        viewModelScope.launch {
            val n = repository.getFake()
            newsFakeResponse.value = n
        }
    }

    fun getAllCrops(){
        viewModelScope.launch {
            val t = repository.getAllCrop()
            cropResponse.value = t
        }
    }
}