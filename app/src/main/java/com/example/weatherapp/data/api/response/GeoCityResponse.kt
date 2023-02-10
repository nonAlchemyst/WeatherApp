package com.example.weatherapp.data.api.response

import com.google.gson.annotations.SerializedName

data class GeoCityResponse (
    @SerializedName("name") val name: String?,
    @SerializedName("local_names") val localNames: LocalNames?,
    @SerializedName("lat") val lat: Double?,
    @SerializedName("lon") val lon: Double?,
    @SerializedName("country") val country: String?,
    @SerializedName("state") val state: String?
) {
    data class LocalNames(
        @SerializedName("ru") val ru: String?
    )
}