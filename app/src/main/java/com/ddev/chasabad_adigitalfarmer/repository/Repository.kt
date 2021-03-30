package com.ddev.chasabad_adigitalfarmer.repository

import com.ddev.chasabad_adigitalfarmer.model.news.NewsData
import com.ddev.chasabad_adigitalfarmer.model.weatherDailyModel.WeatherUviData
import com.ddev.chasabad_adigitalfarmer.model.weatherModel.WeatherData
import com.ddev.chasabad_adigitalfarmer.network.ApiClient
import retrofit2.Response

class Repository {
    suspend fun getData(lat: String,lon: String,appid: String):Response<WeatherData>{
        return ApiClient.api.getWeatherData(lat,lon,appid)
    }

    suspend fun getUviData(lat: String,lon: String,appid: String):Response<WeatherUviData>{
        return ApiClient.api.getDailyWeatherData(lat,lon,appid)
    }

    suspend fun getNews(apiKey: String):Response<NewsData> {
        return ApiClient.newsApi.getNews(apiKey)
    }
}