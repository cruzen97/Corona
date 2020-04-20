package com.example.coronavirus_tracker.UI

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coronavirus_tracker.Data.CoronaClient
import com.example.coronavirus_tracker.Models.Complete
import com.example.coronavirus_tracker.Models.Resume
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoronaViewModel: ViewModel(){

    var mutableResumeLiveData: MutableLiveData<Resume> = MutableLiveData()
    var mutableCompleteLiveData: MutableLiveData<ArrayList<Complete>> = MutableLiveData()


    fun getCoronaWorldInformation(){

        CoronaClient.coronaClient!!.coronaResumeInformation.enqueue(object : Callback<Resume> {
            override fun onFailure(call: Call<Resume>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }

            override fun onResponse(call: Call<Resume>, response: Response<Resume>) {
                if (response.isSuccessful && response.body() != null && response.body().toString()
                        .isNotEmpty()
                ) {
                    mutableResumeLiveData.postValue(
                        Resume(
                            response.body()!!.recovered,
                            response.body()!!.cases,
                            response.body()!!.deaths,
                            response.body()!!.todayCases,
                            response.body()!!.todayDeaths,
                            response.body()!!.active,
                            response.body()!!.critical,
                            response.body()!!.casesPerOneMillion,
                            response.body()!!.deathsPerOneMillion,
                            response.body()!!.tests,
                            response.body()!!.testsPerOneMillion,
                            response.body()!!.affectedCountries
                        )
                    )
                }

            }
        })
    }

    fun getCoronaCompleteInformation(){



        val mData: ArrayList<Complete> = ArrayList()
        CoronaClient.coronaClient!!.coronaCompleteInformation.enqueue(object : Callback<List<Complete>>{
            override fun onFailure(call: Call<List<Complete>>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<Complete>>,
                response: Response<List<Complete>>
            ) {
                if (response.body() != null) {
                    for (i in response.body()!!.indices) {

                        mData.add(
                            Complete(
                                response.body()!![i].country,
                                response.body()!![i].recovered,
                                response.body()!![i].cases,
                                response.body()!![i].critical,
                                response.body()!![i].deaths,
                                response.body()!![i].todayCases,
                                response.body()!![i].todayDeaths,
                                response.body()!![i].tests,
                                response.body()!![i].countryInfo,
                                response.body()!![i].casesPerOneMillion,
                                response.body()!![i].deathsPerOneMillion,
                                response.body()!![i].testsPerOneMillion
                            )
                        )
                        mutableCompleteLiveData.value = mData
                    }
                }
            }
        })
    }
}