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
    val temp_c: Float,
    @SerializedName("feels_like")
    val feels_like_c: Float,
    @SerializedName("temp_min")
    val temp_min_c: Float,
    @SerializedName("temp_max")
    val temp_max_c: Float,
    val humidity: Int
)
