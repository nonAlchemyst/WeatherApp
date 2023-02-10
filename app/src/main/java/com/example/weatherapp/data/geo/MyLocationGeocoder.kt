package com.example.weatherapp.data.geo

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import javax.inject.Inject

class MyLocationGeocoder @Inject constructor(
    private val context: Context
    ){

    companion object{
        const val GPS_NOT_ENABLED_ERROR = 1
        const val PERMISSION_NOT_GRANTED_ERROR = 2
        const val MIN_TIME_MS = 2000L
        const val MIN_DISTANCE_M = 1000F
    }

    private var locationUpdateManager: LocationManager? = null
    private var locationUpdateListener: LocationListener? = null

    fun resume(listener: IGeocoder){
        locationUpdateListener = object: LocationListener{
            override fun onLocationChanged(location: Location) {
                listener.onSuccess(location)
            }

            override fun onProviderEnabled(provider: String) {
                listener.onProviderEnabled(provider)
            }

            override fun onProviderDisabled(provider: String) {
                listener.onProviderDisabled(provider)
            }

            @Deprecated("Deprecated in Java")
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        }
        locationUpdateManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            listener.onError(PERMISSION_NOT_GRANTED_ERROR, "Permission is not granted")
            return
        }
        locationUpdateManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            MIN_TIME_MS,
            MIN_DISTANCE_M, locationUpdateListener!!)
        if(isProviderDisabled(locationUpdateManager)){
            listener.onError(GPS_NOT_ENABLED_ERROR, "GPS is not enabled")
            return
        }
    }

    fun cancel(){
        removeOnLocationUpdateListener()
    }

    fun isProviderDisabled(): Boolean{
        return isProviderDisabled(locationUpdateManager)
    }

    private fun isProviderDisabled(locationManager: LocationManager?): Boolean{
        return locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == false || locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) == false
    }

    private fun removeOnLocationUpdateListener(){
        locationUpdateListener?.let {
            locationUpdateManager?.removeUpdates(it)
        }
    }

    interface IGeocoder{
        fun onError(errorCode: Int, message: String)
        fun onSuccess(location: Location){}
        fun onProviderEnabled(provider: String){}
        fun onProviderDisabled(provider: String){}
    }
}
