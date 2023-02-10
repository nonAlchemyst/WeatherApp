package com.example.weatherapp.data.repositories

import com.example.weatherapp.domain.common.Result
import com.example.weatherapp.domain.entities.city.Cities
import com.example.weatherapp.domain.entities.city.City
import com.example.weatherapp.domain.repositories.GeocodingRepository
import javax.inject.Inject

class GeocodingRepositoryImpl @Inject constructor(
    private val geocodingLocalDataSource: GeocodingLocalDataSource,
    private val geocodingRemoteDataSource: GeocodingRemoteDataSource
): GeocodingRepository {

    override suspend fun getCitiesByName(cityName: String): Result<Cities> {
        val local = geocodingLocalDataSource.loadCitiesByName(cityName)
        if(local is Result.Success){
            if(local.data.names.isNotEmpty())
                return local
        }
        return geocodingRemoteDataSource.getCitiesByName(cityName)
    }

    override suspend fun getCurrentCity(): Result<City> =
        geocodingRemoteDataSource.getCurrentCity()

}

