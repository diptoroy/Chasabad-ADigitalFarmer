package com.ddev.chasabad.model.weatherModel

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)