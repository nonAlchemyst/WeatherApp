package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repositories.WeatherRepository

class GetWeatherForecastUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(cityName: String) =
        weatherRepository.get5DayWeatherForecast(cityName)
}