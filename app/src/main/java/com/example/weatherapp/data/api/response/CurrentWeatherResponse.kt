package com.example.weatherapp.data.api.response

import com.example.weatherapp.data.api.response.common.*
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("coord"     ) var coord      : Coord?             = Coord(),
    @SerializedName("weather"   ) var weather    : ArrayList<Weather> = arrayListOf(),
    @SerializedName("base"      ) var base       : String?            = null,
    @SerializedName("main"      ) var main       : Main?              = Main(),
    @SerializedName("visibility") var visibility : Int?               = null,
    @SerializedName("wind"      ) var wind       : Wind?              = Wind(),
    @SerializedName("clouds"    ) var clouds     : Clouds?            = Clouds(),
    @SerializedName("dt"        ) var dt         : Int?               = null,
    @SerializedName("name"      ) var name       : String?            = null,
)