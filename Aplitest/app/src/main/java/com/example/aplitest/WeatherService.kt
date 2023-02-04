package com.example.aplitest

import com.google.gson.JsonObject
import retrofit2.Call;
import retrofit2.http.GET;
interface WeatherService {
    @GET("?q=Montpellier&appid=ee2b8d7cd97c82ff15d117e5556e5eb3&units=metric")
    fun getWeatherByCity(): Call<JsonObject>
}
