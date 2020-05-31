package com.se_prashant.preventionfromcovid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.barteksc.pdfviewer.PDFView
import com.se_prashant.preventionfromcovid.databinding.FragmentHelplineBinding

/**
 * A simple [Fragment] subclass.
 */
class HelplineFragment : Fragment() {
    var pdfView: PDFView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       val binding  =DataBindingUtil.inflate<FragmentHelplineBinding>(
           inflater,R.layout.fragment_helpline,container,false)
        pdfView = binding.pdfView
        pdfView!!.fromAsset("coronvavirushelplinenumber.pdf").load()
        (activity as AppCompatActivity).supportActionBar?.title = "Helpline No."
        return binding.root
    }

}
