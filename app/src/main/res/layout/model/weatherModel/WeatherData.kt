package com.ddev.chasabad.model.weatherModel

import com.ddev.chasabad_adigitalfarmer.model.weatherModel.Clouds
import com.ddev.chasabad_adigitalfarmer.model.weatherModel.Coord
import com.ddev.chasabad_adigitalfarmer.model.weatherModel.Main
import com.ddev.chasabad_adigitalfarmer.model.weatherModel.Sys
import com.ddev.chasabad_adigitalfarmer.model.weatherModel.Weather
import com.ddev.chasabad_adigitalfarmer.model.weatherModel.Wind

data class WeatherData(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)