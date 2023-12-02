package com.example.tp5.dailyWeather

import com.gl4.tp5.model.FeelsLike
import com.gl4.tp5.model.Temp
import com.gl4.tp5.model.WeatherX


data class DailyDetail(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val feels_like: FeelsLike,
    val gust: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<WeatherX>
)