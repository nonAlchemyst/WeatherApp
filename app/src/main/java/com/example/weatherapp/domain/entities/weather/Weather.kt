package com.example.weatherapp.domain.entities.weather

data class Weather(
    val temperature: Temperature,
    val pressure: Int,
    val wind: Wind,
    val clouds: Int,
    val dt: Long
){
    data class Temperature(
        val temp: Int,
        val feelsLike: Int
    )

    data class Wind(
        val speed: Float,
        val gust: Float
    )

}
