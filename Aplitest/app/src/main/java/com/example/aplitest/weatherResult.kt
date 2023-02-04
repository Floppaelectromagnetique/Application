package com.example.aplitest

import com.google.android.material.circularreveal.CircularRevealHelper.Strategy

data class weatherResult(
    var name: String,
    var main: MainJson,
    var weather: Array<WeatherJson>
)

data class MainJson(
    var temp: Double,
    var humidity: Double,
    var feels_like: Double
)

data class WeatherJson(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)
