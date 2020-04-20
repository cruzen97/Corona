package com.example.coronavirus_tracker.Data

import com.example.coronavirus_tracker.Models.Complete
import com.example.coronavirus_tracker.Models.Resume
import retrofit2.Call
import retrofit2.http.GET

interface
CoronaInterface{

    @GET("all")
    fun getCoronaVirusResumeInformation(): Call<Resume>
    @GET("countries")
    fun getCoronaVirusCompleteInformation(): Call<List<Complete>>

}

