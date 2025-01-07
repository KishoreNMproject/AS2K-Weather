package com.as2k.Weather.model


data class DayWeather(
    val temp: Temp,
    val weather: List<Weather>
)

data class Temp(
    val min: Float,
    val max: Float
)

data class Weather(
    val description: String,
    val icon: String
)
