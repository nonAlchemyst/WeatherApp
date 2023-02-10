package com.example.weatherapp.data.repositories

import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.domain.common.Result
import com.example.weatherapp.domain.entities.city.Cities
import javax.inject.Inject

interface GeocodingLocalDataSource {
    suspend fun loadCitiesByName(cityName: String): Result<Cities>
    suspend fun saveCities(cities: Cities)
}

class GeocodingLocalDataSourceImpl @Inject constructor(
    private val mapper: Mapper
): GeocodingLocalDataSource {

    override suspend fun loadCitiesByName(cityName: String): Result<Cities> {
        return Result.Error(Exception("TODO(\"Not yet implemented\")"))
    }

    override suspend fun saveCities(cities: Cities) {
        TODO("Not yet implemented")
    }

}