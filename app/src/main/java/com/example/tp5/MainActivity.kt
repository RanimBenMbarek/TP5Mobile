package com.example.tp5


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tp5.databinding.ActivityMainBinding
import com.example.tp5.weather.weatherResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner = binding.Spinner

        // Observe the LiveData using the viewLifecycleOwner
        weatherViewModel.weather.observe(this) { weatherResponse ->
            weatherResponse?.let {
                configWeather(it)
            }
        }

        val cities = listOf("Tunisie", "London", "Paris")
        spinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cities)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCity = cities[position]
                weatherViewModel.changeCity(selectedCity)
                if(weatherViewModel.weather.value != null){
                    configWeather(weatherViewModel.weather.value!!)
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                // Handle case when nothing is selected
            }
        }
        binding.Button.setOnClickListener{
            val intent = Intent(this, DailyActivity::class.java)
            intent.putExtra("city", spinner.selectedItem.toString())
            startActivity(intent)
        }
    }

    private fun configWeather(it: weatherResponse) {
        binding.cityTextView.text = it.name
        binding.temperatureTextView.text = "${it.main.temp.toString()}°C"
        binding.weatherTextView.text = it.weather[0].description
        binding.humidityTextView.text = "Humidity : ${it.main.humidity}"
        binding.pressureTextView.text = "Pressure : ${it.main.pressure}"
    }
}


