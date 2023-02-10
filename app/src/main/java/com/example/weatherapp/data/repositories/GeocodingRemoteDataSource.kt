package com.example.weatherapp.data.repositories

import com.example.weatherapp.data.api.NetworkService
import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.data.geo.GeoRepository
import com.example.weatherapp.data.geo.GeoResult
import com.example.weatherapp.domain.common.Result
import com.example.weatherapp.domain.entities.city.Cities
import com.example.weatherapp.domain.entities.city.City
import javax.inject.Inject
import com.example.weatherapp.di.AppComponent

interface GeocodingRemoteDataSource {
    suspend fun getCitiesByName(cityName: String): Result<Cities>
    suspend fun getCurrentCity(): Result<City>
}

class GeocodingRemoteDataSourceImpl @Inject constructor(
    private val networkService: NetworkService,
    private val geoRepository: GeoRepository,
    private val mapper: Mapper
): GeocodingRemoteDataSource {

    override suspend fun getCitiesByName(cityName: String): Result<Cities> {
        val result = networkService.handleRequest {
            networkService.retrofitService.getCitiesByName(cityName)
        }
        return mapper.mapCityResponseToCitiesDomain(result)
    }

    override suspend fun getCurrentCity(): Result<City> {
       return when(val coordinates =
           geoRepository.coordinates.value ?: geoRepository.getCoordinates()
       ){
            is GeoResult.Success        -> {
                val lat = coordinates.data.latitude
                val lon = coordinates.data.longitude
                val result = networkService.handleRequest {
                    networkService.retrofitService.getCityByCoords(lat, lon)
                }
                mapper.mapCityResponseToCityDomain(result)
            }
            is GeoResult.Error          -> {
                Result.Error(coordinates.ex)
            }
            is GeoResult.ProviderAction -> {
                Result.Error(Exception("Provider enabled: ${coordinates.enabled}"))
            }
        }
    }

}