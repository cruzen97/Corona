package com.example.coronavirus_tracker.UI

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coronavirus_tracker.Data.CoronaClient
import com.example.coronavirus_tracker.Models.Complete
import com.example.coronavirus_tracker.Models.Resume
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CoronaViewModel: ViewModel(){

    var mutableResumeLiveData: MutableLiveData<Resume> = MutableLiveData()
    var mutableCompleteLiveData: MutableLiveData<ArrayList<Complete>> = MutableLiveData()


    fun getCoronaCompleteInformation(){

        val mData: ArrayList<Complete> = ArrayList()
        CoronaClient.coronaClient!!.coronaCompleteInformation.enqueue(object : Callback<List<Complete>>{
            override fun onFailure(call: Call<List<Complete>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
                                response.body()!![i].todayDeaths
                            )
                        )
                        mutableCompleteLiveData.setValue(mData)
                    }

                }

            }
        })
    }
}