package com.example.aplitest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
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
    lateinit var mainLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editCityName = findViewById(R.id.searchtown)
        Search = findViewById(R.id.Search)
        imageWeather = findViewById(R.id.imageWeather)
        temperature = findViewById(R.id.temp)
        ville = findViewById(R.id.ville)
        mainLayout = findViewById(R.id.mainLayout)
        Search.setOnClickListener{
            val town = editCityName.text.toString()
            if (town.isEmpty()) {
                Toast.makeText(this, "Entrez une ville pour rechercher", Toast.LENGTH_SHORT).show()
            } else {
                getWeatherInfo(town)
            }
        }


    }
    fun getWeatherInfo(town: String) {
        //TODO: create retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/weather/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherService::class.java)


        //TODO: call weather api
        val result = weatherService.getWeatherByCity(town)
        result.enqueue(object : Callback<weatherResult> {
            override fun onResponse(call: Call<weatherResult>, response: Response<weatherResult>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    temperature.text = "${result?.main?.temp} °C"
                    ville.text = result?.name
                    Picasso.get().load("https://openweathermap.org/img/w/${result?.weather?.get(0)?.icon}.png").into(imageWeather)

                    mainLayout.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<weatherResult>, t: Throwable) {
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