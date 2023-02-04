package com.example.aplitest

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var editCityName: EditText
    lateinit var Search: Button
    lateinit var imageWeather : ImageView
    lateinit var temperature: TextView
    lateinit var ville: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editCityName = findViewById(R.id.searchtown)
        Search = findViewById(R.id.Search)
        imageWeather = findViewById(R.id.imageWeather)
        temperature = findViewById(R.id.temp)
        ville = findViewById(R.id.ville)

        //TODO: create retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/weather/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherService::class.java)

        //TODO: call weather api
        val result = weatherService.getWeatherByCity()
        result.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    val main = result?.get("main")?.asJsonObject
                    val temp = main?.get("temp")?.asDouble
                    val feeling = main?.get("feels_like")?.asDouble

                    val weather = result?.get("weather")?.asJsonArray
                    val icon = weather?.get(0)?.asJsonObject?.get("icon")?.asString
                    Picasso.get().load("https://openweathermap.org/img/w/$icon.png").into(imageWeather)

                    temperature.text = "$temp °C"
                    ville.text = "ressentie: $feeling"
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(applicationContext, "Erreur Serveur", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

fun find_name(i: Int, sentence: String) {
    while (sentence[i] != '"')
        print(sentence[i])
    println(" ")
}

fun Find_Info(str: String) {
    val sentence = str
    val length = sentence.length
    for (i in 0..length) {
        if (sentence.contains("name", true))
            find_name(i, sentence)
    }
}