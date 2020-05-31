package com.se_prashant.covid19_tracker

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.text.set

class SpannableDelta(text:String,color:Int,start:Int):SpannableString(text){

    init {
        setSpan(
            ForegroundColorSpan(color),
            start,
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}