package com.example.tp5.dailyWeather

import com.gl4.tp5.model.City

data class dailyWeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DailyDetail>,
    val message: Double
)