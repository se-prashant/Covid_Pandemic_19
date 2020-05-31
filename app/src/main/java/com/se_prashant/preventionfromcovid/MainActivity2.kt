package com.se_prashant.preventionfromcovid

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.se_prashant.covid19_tracker.StateAdapter
import com.se_prashant.covid19_tracker.StatewiseItem
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.item_list.activeTv
import kotlinx.android.synthetic.main.item_list.confirmedTv
import kotlinx.android.synthetic.main.item_list.recoveredTv
import okhttp3.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {
    lateinit var stateAdapter : StateAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.item_header, list, false))
        fetchResults()

    }

    private fun fetchResults() {

        val okHttpClient= OkHttpClient()
        val request = Request.Builder()
            .url("https://api.covid19india.org/data.json")
            .build()

        val api = okHttpClient
        api.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Issue")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = Gson().fromJson(
                    response.body?.string(),
                    com.se_prashant.covid19_tracker.Response::class.java
                )
                runOnUiThread{
                    bindCombineDate(data.statewise[0])
                    bindStateWiseData(data.statewise.subList(1, data.statewise.size))
                }
            }
        })

    }




    private fun bindStateWiseData(subList: List<StatewiseItem>) {
        stateAdapter = StateAdapter(subList)
        list.adapter = stateAdapter
    }
    private fun bindCombineDate(data: StatewiseItem){
        val lastUpdatedTime:String? = data.lastupdatedtime
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        lastUpdatedTv.text = "Last updated\n ${getTimeAgo(simpleDateFormat.parse(lastUpdatedTime))}"

        confirmedTv.text = data.confirmed
        recoveredTv.text = data.recovered
        activeTv.text = data.active
        deceasedTv.text = data.deaths

    }
    fun getTimeAgo(past: Date): String {
        val now = Date()
        val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
        val hours = TimeUnit.MILLISECONDS.toHours(now.time - past.time)

        return when {
            seconds < 60 -> {
                "Few seconds ago"
            }
            minutes < 60 -> {
                "$minutes minutes ago"
            }
            hours < 24 -> {
                "$hours hour ${minutes % 60} min ago"
            }
            else -> {
                SimpleDateFormat("dd/MM/yy, hh:mm a").format(past).toString()
            }
        }
    }

}
