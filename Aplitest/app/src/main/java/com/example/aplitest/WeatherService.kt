package com.example.aplitest

import com.google.gson.JsonObject
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query

interface WeatherService {
    companion object {
        const val API_KEY = "ee2b8d7cd97c82ff15d117e5556e5eb3"
    }
    @GET("?&appid=$API_KEY&units=metric")
    fun getWeatherByCity(@Query("q") city: String): Call<weatherResult>
}
