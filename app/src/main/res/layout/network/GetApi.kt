package com.ddev.chasabad.network

import com.ddev.chasabad_adigitalfarmer.model.weatherDailyModel.WeatherUviData
import com.ddev.chasabad_adigitalfarmer.model.weatherModel.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetApi {
    @GET("data/2.5/weather?")
    suspend fun getWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
    ): Response<WeatherData>

    @GET("data/2.5/onecall?")
    suspend fun getDailyWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
    ): Response<WeatherUviData>
}