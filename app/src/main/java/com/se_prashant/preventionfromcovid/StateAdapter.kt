package com.se_prashant.covid19_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.se_prashant.preventionfromcovid.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_list.view.confirmedTv
import kotlinx.android.synthetic.main.item_list.view.recoveredTv
import kotlinx.android.synthetic.main.item_list.view.activeTv as activeTv1

class StateAdapter (val list:List<StatewiseItem>):BaseAdapter(){
    override fun getView(p0: Int, p1: View?, p2: ViewGroup): View {
        val view = p1 ?: LayoutInflater.from(p2.context).inflate(R.layout.item_list, p2, false)
            val item = list[p0]
            view.confirmedTv.text = SpannableDelta(
                "${item.confirmed}\n ↑${item.deltaconfirmed ?: "0"}",
                R.color.dark_red,
                item.confirmed?.length ?: 0
            )

            view.recoveredTv.text = SpannableDelta(
                "${item.recovered}\n ↑${item.deltarecovered ?: "0"}",
                R.color.dark_blue,
                item.recovered?.length ?: 0
            )

            view.deathsTv.text = SpannableDelta(
                "${item.deaths}\n ↑${item.deltadeaths ?: "0"}",
                R.color.dark_blue,
                item.deaths?.length ?: 0
            )

            view.activeTv1.text = SpannableDelta(
                "${item.active}\n ↑${item.deltaactive ?: "0"}",
                R.color.dark_green,
                item.active?.length ?: 0
            )

            view.stateTv.text = item.state

            return view
        }
    override fun getItem(p0: Int) = list[p0]

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getCount() = list.size

}


