package com.se_prashant.preventionfromcovid

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager
import com.se_prashant.preventionfromcovid.databinding.FragmentCheckSymptomBinding

/**
 * A simple [Fragment] subclass.
 */

//Check Precautions we should follow
class checkSymptom : Fragment() {
    private var mSlideViewPager: ViewPager? = null
    private var mDotLayout: LinearLayout? = null
    private var nextButton: Button? = null
    private var backButton: Button? = null
    private lateinit var mDots: Array<TextView?>
    private var slideAdapters: SlideAdapter? = null
    private var nCurrentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     val binding = DataBindingUtil.inflate<FragmentCheckSymptomBinding>(inflater,
         R.layout.fragment_check_symptom,container,false)
        mSlideViewPager = binding.slideViewPAger as ViewPager
        mDotLayout = binding.dotsLayout as LinearLayout
        nextButton = binding.next as Button
        backButton = binding.previous
        slideAdapters = activity?.let { SlideAdapter(it) }
        mSlideViewPager!!.adapter = slideAdapters
        addDotIndicator(0)
        mSlideViewPager!!.addOnPageChangeListener(viewListner)
        nextButton!!.setOnClickListener { mSlideViewPager!!.currentItem = nCurrentPage + 1 }
        backButton!!.setOnClickListener(View.OnClickListener { mSlideViewPager!!.currentItem= nCurrentPage-1 })

        (activity as AppCompatActivity).supportActionBar?.title = "Prevention"
        return binding.root
    }
    fun addDotIndicator(position: Int) {
        mDots = arrayOfNulls(6)
        mDotLayout!!.removeAllViews()
        for (i in mDots.indices) {
            mDots[i] = TextView(activity)
            mDots[i]!!.text = Html.fromHtml("&#8226")
            mDots[i]!!.textSize = 35f
            mDots[i]!!.setTextColor(resources.getColor(R.color.colorWhite))
            mDotLayout!!.addView(mDots[i])
        }
        if (mDots.size > 0) {
            mDots[position]!!.setTextColor(resources.getColor(R.color.colorWhite))
        }
    }

    var viewListner: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        override fun onPageSelected(position: Int) {
            addDotIndicator(position)
            nCurrentPage = position
            if (position == 0) {
                nextButton!!.isEnabled = true
                backButton!!.isEnabled = false
                backButton!!.visibility = View.INVISIBLE
                nextButton!!.text = "Next"
                backButton!!.text = ""
            } else if (position == mDots.size - 1) {
                nextButton!!.isEnabled = true
                backButton!!.isEnabled = true
                backButton!!.visibility = View.VISIBLE
                nextButton!!.text = "Finish"
                backButton!!.text = "Back"
                nextButton!!.setOnClickListener{ view:View->
                    view.findNavController().navigate(R.id.action_checkSymptom_to_titleFragment)
                }
            } else {
                nextButton!!.isEnabled = true
                backButton!!.isEnabled = true
                backButton!!.visibility = View.VISIBLE
                nextButton!!.text = "Next"
                backButton!!.text = "Back"
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
}



