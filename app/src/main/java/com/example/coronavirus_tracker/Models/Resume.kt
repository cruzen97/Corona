package com.example.coronavirus_tracker.Models

import com.google.gson.annotations.SerializedName


data class Resume (
    @SerializedName("recovered") var recovered: String,
    @SerializedName("cases") var cases: String,
    @SerializedName("deaths") var deaths: String,
    @SerializedName("todayCases") var todayCases: String,
    @SerializedName("todayDeaths") var todayDeaths: String,
    @SerializedName("active") var active: String,
    @SerializedName("critical") var critical: String,
    @SerializedName("casesPerOneMillion") var casesPerOneMillion: String,
    @SerializedName("deathsPerOneMillion") var deathsPerOneMillion: String,
    @SerializedName("tests") var tests: String,
    @SerializedName("testsPerOneMillion") var testsPerOneMillion: String,
    @SerializedName("affectedCountries") var affectedCountries: String
)