package com.as2k.Weather.network

import com.as2k.Weather.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    fun getWeather(@Query("q") city: String, @Query("appid") apiKey: String): Call<WeatherResponse>

    @GET("forecast/daily")
    suspend fun getDailyWeather(
        @Query("q") city: String,
        @Query("cnt") days: Int,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>
}