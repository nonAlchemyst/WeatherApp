package com.example.weatherapp.data.api

import com.example.weatherapp.data.api.response.CurrentWeatherResponse
import com.example.weatherapp.data.api.response.GeoCityResponse
import com.example.weatherapp.data.api.response.WeatherForecastResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru"
    ): Deferred<Response<CurrentWeatherResponse>>

    @GET("data/2.5/weather")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru"
    ): Deferred<Response<WeatherForecastResponse>>

    @GET("geo/1.0/direct")
    suspend fun getCitiesByName(
        @Query("q") name: String,
        @Query("limit") limit: Int = 5
    ): Deferred<Response<List<GeoCityResponse>>>

    @GET("geo/1.0/reverse")
    suspend fun getCityByCoords(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("limit") limit: Int = 1
    ): Deferred<Response<List<GeoCityResponse>>>

}