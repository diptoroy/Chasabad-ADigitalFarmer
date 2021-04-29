package com.ddev.chasabad_adigitalfarmer.network

import com.ddev.chasabad_adigitalfarmer.model.allCrop.AllCropData
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.model.crop.CropFertilizer
import com.ddev.chasabad_adigitalfarmer.model.news.Article
import com.ddev.chasabad_adigitalfarmer.model.news.NewsData
import com.ddev.chasabad_adigitalfarmer.model.notification.PushNotification
import com.ddev.chasabad_adigitalfarmer.model.weatherDailyModel.WeatherUviData
import com.ddev.chasabad_adigitalfarmer.model.weatherModel.WeatherData
import com.ddev.chasabad_adigitalfarmer.util.Constants.Companion.NOTIFICATION_CONTENT_KEY
import com.ddev.chasabad_adigitalfarmer.util.Constants.Companion.NOTIFICATION_SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

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

    @GET("v2/top-headlines?country=us")
    suspend fun getNews(
        @Query("apiKey") apikey: String
    ):Response<NewsData>

    @GET("diptoroy/dde397e1937d947ffa32c04a46d737da/raw/4537a46e55bab594b1c9358541ffa2814dc8f998/News%2520App")
    suspend fun getFake():List<Article>

    @GET("diptoroy/b0ba906381012532fc0926f764ee535e/raw/8e9a3e250909cdb87ad4ba1a7f1bf60dfd76a725/crop")
    suspend fun getAllCrop():Response<AllCropData>

    @GET("diptoroy/b0ba906381012532fc0926f764ee535e/raw/e2af193c0f040bd0ffb0f39f60bde023521a14d2/crop")
    suspend fun getCropData():List<CropData>

    @Headers("Authorization: key=$NOTIFICATION_SERVER_KEY","Content-Type:$NOTIFICATION_CONTENT_KEY")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ):Response<ResponseBody>

}