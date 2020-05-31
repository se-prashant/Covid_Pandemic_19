package com.se_prashant.preventionfromcovid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter


class SlideAdapter(var context: Context) : PagerAdapter() {
    var layoutInflater: LayoutInflater? = null
    var slide_image = intArrayOf(
        R.drawable.wash_hand_prio,
        R.drawable.cover_mouth,
        R.drawable.stop_public_gather,
        R.drawable.stophandshake,
        R.drawable.wear_mask,
        R.drawable.stayhome
    )
    var slide_headings = arrayOf(
        "Wash Hands",
        "Cover your Face",
        "Don't gather in group",
        "Stop shaking hands",
        "Wear a (homemade) Mask",
        "Stay Home"
        )
    var silde_detil = arrayOf(
        "Wash your hands with soap and water, or an alcohal-based hand rub for at least 20 sec",
        "Cover your nose and mouth with your bent elbow or a tissue when you cough or sneeze.\n" ,
        "Being in a group or gathering makes it more likely that you will be in close contact with someone.This includes avoiding all religious places of worship, as you may have to sit or stand too close to another congregant.",
        "Never shake hands again, underlining that the practise would not only prevent the spread of the novel coronavirus but also decrease instances of influenza dramatically in the country.",
        "Masks can help prevent people who are asymptomatic or undiagnosed from transmitting SARS-CoV-2 when they breathe, talk, sneeze, or cough. This, in turn, slows the spread of the virus.",
        "Anyone who is sick — even if they don't know for sure they have coronavirus (COVID-19) — should stay home unless they need medical care. This helps prevent the illness from spreading to other people."
        )

    override fun getCount(): Int {
        return slide_headings.size
    }

    override fun isViewFromObject(
        view: View,
        `object`: Any
    ): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View =
            layoutInflater!!.inflate(R.layout.slider_layout, container, false)
        val slideImageView =
            view.findViewById<View>(R.id.imageView2) as ImageView
        val slideHeading =
            view.findViewById<View>(R.id.silde_heading) as TextView
        val slideDes = view.findViewById<View>(R.id.slide_detail) as TextView
        slideImageView.setImageResource(slide_image[position])
        slideHeading.text = slide_headings[position]
        slideDes.text = silde_detil[position]
        container.addView(view)
        return view
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as RelativeLayout)
    }

}
