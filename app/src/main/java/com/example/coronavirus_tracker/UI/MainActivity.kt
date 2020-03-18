package com.example.coronavirus_tracker.UI


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.GridLayoutManager
import com.example.coronavirus_tracker.Adapter.CoronaAdapter
import com.example.coronavirus_tracker.Models.Complete
import com.example.coronavirus_tracker.R
import com.example.coronavirus_tracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mLayout: RecyclerView.LayoutManager
    lateinit var mCoronaAdapter: CoronaAdapter
    lateinit var mDataList: ArrayList<Complete>
    lateinit var mainBinding: ActivityMainBinding
    lateinit var mCoronaViewModel: CoronaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCoronaViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CoronaViewModel::class.java)
        mCoronaViewModel.getCoronaCompleteInformation()
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupRecyclerView(mainBinding.recyclerCorona)
        mCoronaViewModel.mutableCompleteLiveData.observe(this, object : Observer<ArrayList<Complete>> {
            override fun onChanged(completes: ArrayList<Complete>) {
                mDataList = completes
                mCoronaAdapter =
                    CoronaAdapter(
                        applicationContext,
                        mDataList)

                mainBinding.recyclerCorona.adapter = mCoronaAdapter
            }
        })

    }

    fun setupRecyclerView(mRecyclerView: RecyclerView) {
        mLayout = GridLayoutManager(getApplicationContext(), 1)
        mRecyclerView.setLayoutManager(mLayout)
        mRecyclerView.setHasFixedSize(true)
    }



}
