package com.ddev.chasabad.model.weatherDailyModel

import com.ddev.chasabad_adigitalfarmer.model.weatherDailyModel.Current
import com.ddev.chasabad_adigitalfarmer.model.weatherDailyModel.Daily
import com.ddev.chasabad_adigitalfarmer.model.weatherDailyModel.Hourly
import com.ddev.chasabad_adigitalfarmer.model.weatherDailyModel.Minutely

data class WeatherUviData(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely>,
    val timezone: String,
    val timezone_offset: Int
)