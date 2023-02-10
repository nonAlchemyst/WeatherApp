package com.example.weatherapp.data.geo

import android.location.Location

sealed class GeoResult {

    data class Success(val data: Location): GeoResult()
    data class ProviderAction(val enabled: Boolean): GeoResult()
    data class Error(val ex: Exception, val errorCode: Int = -1): GeoResult()

}
