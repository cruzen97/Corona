package com.example.coronavirus_tracker.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.scaleMatrix
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.coronavirus_tracker.Models.Complete
import com.example.coronavirus_tracker.R

class CoronaAdapter (
    var mContext:Context,
    var data: ArrayList<Complete>):
    RecyclerView.Adapter<CoronaAdapter.CoronaViewHolder>(), Filterable{

    internal var filterListResults :List<Complete> = data

    override fun getFilter(): Filter {

        return object:Filter(){
            override fun performFiltering(charString: CharSequence?): FilterResults {
                var charSearch = charString.toString()
                filterListResults = if(charSearch.isEmpty()){
                    data

                } else {
                    val resultsList = ArrayList<Complete>()
                    for( row in data){
                        if(row.country.toLowerCase().contains(charSearch.toLowerCase()))
                            resultsList.add(row)
                    }
                    resultsList
                }
                val filterResults = FilterResults()
                filterResults.values = filterListResults
                return filterResults
            }

            override fun publishResults(charSquence: CharSequence?, filterResults: FilterResults?) {
                filterListResults = filterResults!!.values as List<Complete>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoronaViewHolder {
        val mView: View = LayoutInflater.from(mContext).inflate(R.layout.item_new, parent, false)
        return CoronaViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return filterListResults.size
    }

    override fun onBindViewHolder(holder: CoronaViewHolder, position: Int) {
        holder.onBind(position, filterListResults,mContext)
        val isExpended: Boolean? = filterListResults[position].expanded
        holder.expandableLayout.visibility = if (isExpended!!) View.VISIBLE else View.GONE
    }

    class CoronaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val mCountry: TextView = itemView.findViewById(R.id.Country)
        private val mNum: TextView = itemView.findViewById(R.id.Num)
        private val mFlag: ImageView = itemView.findViewById(R.id.imageView_flag)
        private val mDeaths: TextView = itemView.findViewById(R.id.deaths_ID)
        private val mRecovered: TextView = itemView.findViewById(R.id.recovered_ID)
        private val mTodayCases: TextView = itemView.findViewById(R.id.todayCases_ID)
        private val mTodayDeaths: TextView = itemView.findViewById(R.id.todayDeaths_ID)
        private val mTests: TextView = itemView.findViewById(R.id.tests_ID)
        private val mCasesPerOneMillion: TextView = itemView.findViewById(R.id.casesPerOneMillion_ID)
        private val mDeathsPerOneMillion: TextView = itemView.findViewById(R.id.deathsPerOneMillion_ID)
        private val mTestsPerOneMillion: TextView = itemView.findViewById(R.id.testsPerOneMillion_ID)
        private val mCritical: TextView = itemView.findViewById(R.id.critical_ID)
        private val mCases: TextView = itemView.findViewById(R.id.cases_ID)
        val expandableLayout :CardView = itemView.findViewById(R.id.CardViewBottom)
        private val cardViewTop :CardView = itemView.findViewById(R.id.CardViewTop)



        @SuppressLint("SetTextI18n")
        fun onBind(
            position: Int,
            data: List<Complete>, mContext: Context
        ) {

            itemView.animation = AnimationUtils.loadAnimation(mContext,R.anim.fade_animiation)
            mNum.text = String.format("%,d", Integer.parseInt(data[position].cases))
            if(data[position].country.length <16){
                mCountry.text = data[position].country
            }
            else{
                mCountry.text = data[position].country.take(15) + ".."
            }
            mDeaths.text = data[position].deaths
            mRecovered.text = data[position].recovered
            mTodayCases.text = data[position].todayCases
            mTodayDeaths.text = data[position].todayDeaths
            mTests.text = data[position].tests
            mCasesPerOneMillion.text = data[position].casesPerOneMillion
            mDeathsPerOneMillion.text = data[position].deathsPerOneMillion
            mTestsPerOneMillion.text = data[position].testsPerOneMillion
            mCritical.text = data[position].critical
            mCases.text = data[position].cases

            cardViewTop.setOnClickListener{
                val isExpended: Boolean? = data[position].expanded
                expandableLayout.visibility = if (isExpended!!) View.GONE else View.VISIBLE
                data[position].expanded = !isExpended
            }
            Glide.with(mContext)
                .load(data[position].countryInfo.flag)
                .apply(RequestOptions().circleCrop())
                .into(mFlag)
        }
    }
}


