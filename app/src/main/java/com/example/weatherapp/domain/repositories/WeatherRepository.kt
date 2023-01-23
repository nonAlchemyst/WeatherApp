package com.example.weatherapp.domain.repositories

import com.example.weatherapp.domain.common.Result
import com.example.weatherapp.domain.entities.weather.Weather
import com.example.weatherapp.domain.entities.weather.WeatherForecast

interface WeatherRepository {

    suspend fun getCurrentWeather(cityName: String): Result<Weather>

    suspend fun get5DayWeatherForecast(cityName: String): Result<WeatherForecast>

}