package com.example.weather_app.data.model

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    val name: String,
    val sys: Sys,
    val weather: List<Weather>,
    val main: Main
)

data class Sys(
    val country: String
)

data class Weather(
    val main: String,
    val icon: String
)

data class Main(
    @SerializedName("temp")
    val temp_c: Float
)
