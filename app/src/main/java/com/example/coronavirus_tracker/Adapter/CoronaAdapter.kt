package com.example.coronavirus_tracker.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coronavirus_tracker.Models.Complete
import com.example.coronavirus_tracker.R
import kotlinx.android.synthetic.main.item.view.*

class CoronaAdapter (

    var mContext:Context,
    var data: ArrayList<Complete>
):RecyclerView.Adapter<CoronaAdapter.CoronaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoronaViewHolder {
        val mView: View = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false)
        return CoronaViewHolder(mView)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CoronaViewHolder, position: Int) {
        holder.onBind(position, data)
    }


    class CoronaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        override fun onClick(p0: View?) {
        println("asd")
        }


        var data: ArrayList<Complete> = ArrayList()
        val mCountry: TextView = itemView.findViewById(R.id.Country)
        val mNum: TextView = itemView.findViewById(R.id.Num)
        val mDeaths: TextView = itemView.findViewById(R.id.Deaths)
        val mRecovered: TextView = itemView.findViewById(R.id.Recovered)
        val mTodayCases: TextView = itemView.findViewById(R.id.TodayCases)
        val mTodayDeaths: TextView = itemView.findViewById(R.id.TodayDeaths)
        val mTodayRecovered: TextView = itemView.findViewById(R.id.TodayRecovered)

        fun onBind(
            position: Int,
            data: ArrayList<Complete>
        ) {
            this.data = data
            mNum.setText(String.format("%,d", Integer.parseInt(data.get(position).cases)))
            mCountry.setText(data.get(position).country)
            mDeaths.setText(String.format("%,d", Integer.parseInt(data.get(position).deaths)))
            mRecovered.setText(String.format("%,d", Integer.parseInt(data.get(position).recovered)))
            mTodayCases.setText("+" + String.format("%,d", Integer.parseInt(data.get(position).todayCases)))
            mTodayDeaths.setText("+" + String.format("%,d", Integer.parseInt(data.get(position).todayDeaths)))

        }

    }
}


