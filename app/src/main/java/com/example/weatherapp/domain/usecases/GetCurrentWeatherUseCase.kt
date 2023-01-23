package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repositories.WeatherRepository

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(cityName: String) =
        weatherRepository.getCurrentWeather(cityName)
}