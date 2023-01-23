package com.example.weatherapp.domain.repositories

import com.example.weatherapp.domain.common.Result
import com.example.weatherapp.domain.entities.city.Cities
import com.example.weatherapp.domain.entities.city.City

interface GeocodingRepository {

    suspend fun getCitiesByName(cityName: String): Result<Cities>

    suspend fun getCurrentCity(): Result<City>

}