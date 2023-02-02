package com.example.aplitest

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var tvResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResponse = findViewById(R.id.tvResponse)

        //TODO: create retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/weather/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherService::class.java)

        //TODO: call weather api
        val result = weatherService.getWeatherByCity().also {

            result.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    
                   if(response.isSuccessful) {
                        tvResponse.text = response.body()
                   }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(applicationContext, text: "Erreur serveur", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}