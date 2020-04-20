package com.example.coronavirus_tracker.UI


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.coronavirus_tracker.Adapter.CoronaAdapter
import com.example.coronavirus_tracker.Models.Complete
import com.example.coronavirus_tracker.Models.Resume
import com.example.coronavirus_tracker.R
import com.example.coronavirus_tracker.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mLayout: RecyclerView.LayoutManager
    lateinit var mCoronaAdapter: CoronaAdapter
    private lateinit var mDataList: ArrayList<Complete>
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mCoronaViewModel: CoronaViewModel
    private var searchView:SearchView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportActionBar!!.hide()
        fetchWorldStatistics()
        fetchStatistics()

    }

    private fun fetchWorldStatistics(){
        mCoronaViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(CoronaViewModel::class.java)
        mCoronaViewModel.getCoronaWorldInformation()
        mainBinding = setContentView(this, R.layout.activity_main)
        mCoronaViewModel.mutableResumeLiveData.observe(this, Observer<Resume>{
            world_cases.text = String.format("%,d", Integer.parseInt(it.cases))

        })
    }

    private fun fetchStatistics(){
        mCoronaViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(CoronaViewModel::class.java)

        mCoronaViewModel.getCoronaCompleteInformation()
        mainBinding = setContentView(this, R.layout.activity_main)
        setupRecyclerView(mainBinding.recyclerCorona)

        setupSearchView()

        mCoronaViewModel.mutableCompleteLiveData.observe(this,
            Observer<ArrayList<Complete>> { completes ->
                mDataList = completes
                mDataList.sortByDescending { it.cases.toInt()}
                mCoronaAdapter =
                    CoronaAdapter(
                        applicationContext,
                        mDataList)

                mainBinding.recyclerCorona.adapter = mCoronaAdapter
            })
    }

    override fun onBackPressed() {
        mainBinding.searchView.clearFocus()
        return super.onBackPressed()
    }


    private fun setupSearchView(){


        searchView = search_view
        mainBinding.searchView.isIconifiedByDefault = false
        mainBinding.searchView.isIconified = false
        mainBinding.searchView.clearFocus()
        mainBinding.searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                mCoronaAdapter.filter.filter(p0)

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                mCoronaAdapter.filter.filter(p0)
                return false
            }
        })
    }

    private fun setupRecyclerView(mRecyclerView: RecyclerView) {
        mLayout = GridLayoutManager(applicationContext, 1)
        mRecyclerView.layoutManager = mLayout
        mRecyclerView.setHasFixedSize(true)
    }
}
