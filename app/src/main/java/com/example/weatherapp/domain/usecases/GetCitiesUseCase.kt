package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repositories.GeocodingRepository

class GetCitiesUseCase(private val geocodingRepository: GeocodingRepository) {
    suspend operator fun invoke(cityName: String) =
        geocodingRepository.getCitiesByName(cityName)
}