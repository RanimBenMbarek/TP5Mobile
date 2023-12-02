package com.example.tp5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp5.weather.weatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    private val weatherReponse = MutableLiveData<weatherResponse>()
    var weather : LiveData<weatherResponse> = weatherReponse

    init {
        getWeather("Tunis")
    }

    private fun getWeather(city : String){
        RetrofitHelper.retrofitService.getWeather(city).enqueue(
            object : Callback<weatherResponse>{
                override fun onResponse(
                    call: Call<weatherResponse>,
                    response: Response<weatherResponse>
                ) {
                    if(response.isSuccessful){
                        weatherReponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<weatherResponse>, t: Throwable) {

                }

            }
        )
    }

    fun changeCity(city : String) : String?{
        getWeather(city)
        weather = weatherReponse
        return weatherReponse.value?.name
    }
}


