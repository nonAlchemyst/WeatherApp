package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repositories.GeocodingRepository

class GetCurrentCityUseCase(private val geocodingRepository: GeocodingRepository) {
    suspend operator fun invoke() =
        geocodingRepository.getCurrentCity()
}