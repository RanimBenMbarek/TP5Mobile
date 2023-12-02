package com.example.tp5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp5.databinding.ActivityDailyBinding


class DailyActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDailyBinding
    var dailyViewModel : DailyWeatherViewModel = DailyWeatherViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDailyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val city=intent.getStringExtra("city")

        if(city != null){
            dailyViewModel.getForecast(city)
        }

        dailyViewModel.forecast.observe(this) {
            if (it != null){
                binding.Recycler.adapter = DetailsAdapter(dailyViewModel.forecast.value)
                binding.cityName.text = dailyViewModel.forecast.value!!.city.name
            }
        }

        binding.Recycler.apply {
            layoutManager = LinearLayoutManager(this@DailyActivity)
            adapter = DetailsAdapter(dailyViewModel.forecast.value)
        }
    }
}