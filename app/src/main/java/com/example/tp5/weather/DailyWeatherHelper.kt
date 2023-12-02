import com.example.tp5.DailyWeatherAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DailyWeatherHelper {

    private const val baseUrl ="https://api.openweathermap.org/data/2.5/"
    /**
     * The Retrofit object with Gson converter.
     */
    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        // we need to add converter factory to
        // convert JSON object to Java object
        .build()
    /**
     * A public Api object that exposes the lazy-initialized Retrofit service
     */
    val retrofitService : DailyWeatherAPI by lazy { retrofit.create(DailyWeatherAPI::class.java) }

}