package com.ddev.chasabad_adigitalfarmer.network

import com.ddev.chasabad_adigitalfarmer.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: GetApi by lazy {
        retrofit.create(GetApi::class.java)
    }
}
