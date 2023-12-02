package com.example.tp5

import com.example.tp5.dailyWeather.dailyWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DailyWeatherAPI {

    @GET("forecast/daily?APPID=17db59488cadcad345211c36304a9266")
    fun getWeather(@Query("q") city : String): Call<dailyWeatherResponse>
}