package com.example.coronavirus_tracker.Models

import com.google.gson.annotations.SerializedName


data class Complete(
    @SerializedName("country") val country: String,
    @SerializedName("recovered") val recovered: String,
    @SerializedName("cases") val cases: String,
    @SerializedName("critical") val critical: String,
    @SerializedName("deaths") val deaths: String,
    @SerializedName("todayCases") val todayCases: String,
    @SerializedName("todayDeaths") val todayDeaths: String,
    @SerializedName("tests") val tests: String,
    @SerializedName("countryInfo") val countryInfo: CountryInfo,
    @SerializedName("casesPerOneMillion") val casesPerOneMillion: String,
    @SerializedName("deathsPerOneMillion") val deathsPerOneMillion: String,
    @SerializedName("testsPerOneMillion") val testsPerOneMillion: String,
    var expanded: Boolean? = false

)
data class CountryInfo(
    val id: Long? = null,
    val iso2: String? = null,
    val iso3: String? = null,
    val lat: Double,
    val long: Double,
    val flag: String
)