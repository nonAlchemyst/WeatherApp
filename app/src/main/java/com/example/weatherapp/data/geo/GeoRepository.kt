package com.example.weatherapp.data.geo

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.api.NetworkService
import javax.inject.Inject
import javax.inject.Singleton
import java.io.IOException
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class GeoRepository @Inject constructor(private val geocoder: MyLocationGeocoder) {

    val coordinates = MutableLiveData<GeoResult>()

    suspend fun getCoordinates(): GeoResult {
        if(geocoder.isProviderDisabled()){
            return GeoResult.Error(IOException())
        }
        val result = suspendCoroutine { continuation: Continuation<GeoResult> ->
            geocoder.resume(object: MyLocationGeocoder.IGeocoder {
                override fun onError(errorCode: Int, message: String) {
                    continuation.resume(GeoResult.Error(IOException(message), errorCode))
                }

                override fun onSuccess(location: Location) {
                    coordinates.value = GeoResult.Success(location)
                    continuation.resume(coordinates.value!!)
                }

                override fun onProviderDisabled(provider: String) {
                    continuation.resume(GeoResult.ProviderAction(enabled = false))
                }

                override fun onProviderEnabled(provider: String) {
                    continuation.resume(GeoResult.ProviderAction(enabled = true))
                }
            })
        }
        geocoder.cancel()
        return result
    }

}