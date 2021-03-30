package com.ddev.chasabad_adigitalfarmer.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddev.chasabad_adigitalfarmer.model.news.NewsData
import com.ddev.chasabad_adigitalfarmer.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ArticleActivityViewModel(private val repository: Repository): ViewModel() {
    val newsResponse: MutableLiveData<Response<NewsData>> = MutableLiveData()

    fun getNews(apiKey: String){
        viewModelScope.launch {
            val n = repository.getNews(apiKey)
            newsResponse.value = n
        }
    }
}