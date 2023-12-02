package com.example.tp5


import DailyWeatherHelper
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp5.dailyWeather.dailyWeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DailyWeatherViewModel( private val context : Context): ViewModel() {
    private val forecastResponse = MutableLiveData<dailyWeatherResponse>()
    var forecast : LiveData<dailyWeatherResponse> = forecastResponse


    fun getForecast(location: String){
        DailyWeatherHelper.retrofitService.getWeather(location).enqueue(
            object : Callback<dailyWeatherResponse> {
                override fun onResponse(
                    call: Call<dailyWeatherResponse>,
                    response: Response<dailyWeatherResponse>
                ) {
                    if(response.isSuccessful){
                        forecastResponse.value = response.body()
                        forecast = forecastResponse
                    }
                }

                override fun onFailure(call: Call<dailyWeatherResponse>, t: Throwable) {
                }
            }
        )
    }


}