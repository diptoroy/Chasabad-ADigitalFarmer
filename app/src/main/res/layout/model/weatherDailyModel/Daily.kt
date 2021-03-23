package com.ddev.chasabad.model.weatherDailyModel

import com.ddev.chasabad_adigitalfarmer.model.weatherDailyModel.FeelsLike
import com.ddev.chasabad_adigitalfarmer.model.weatherDailyModel.Temp
import com.ddev.chasabad_adigitalfarmer.model.weatherDailyModel.WeatherX

data class Daily(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: FeelsLike,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val uvi: Double,
    val weather: List<WeatherX>,
    val wind_deg: Int,
    val wind_speed: Double
)