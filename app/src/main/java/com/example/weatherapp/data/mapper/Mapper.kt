package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.api.response.GeoCityResponse
import com.example.weatherapp.domain.entities.city.Cities
import com.example.weatherapp.domain.entities.city.City
import com.example.weatherapp.domain.common.Result
import javax.inject.Inject
import javax.inject.Singleton

class Mapper @Inject constructor() {

    fun mapCityResponseToCitiesDomain(geoCitiesResult: Result<List<GeoCityResponse>>): Result<Cities> =
        when(geoCitiesResult){
            is Result.Success   -> Result.Success(Cities(geoCitiesResult.data.map { City(it.name ?: "") }))
            is Result.Error     -> Result.Error(geoCitiesResult.exception)
        }

    fun mapCityResponseToCityDomain(geoCityResult: Result<List<GeoCityResponse>>): Result<City> =
        when(geoCityResult){
            is Result.Success   -> Result.Success(City(geoCityResult.data[0].name ?: ""))
            is Result.Error     -> Result.Error(geoCityResult.exception)
        }

}