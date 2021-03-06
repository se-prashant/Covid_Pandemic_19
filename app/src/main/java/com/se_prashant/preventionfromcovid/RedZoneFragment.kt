package com.se_prashant.preventionfromcovid

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.se_prashant.preventionfromcovid.databinding.FragmentGreenZoneBinding
import com.se_prashant.preventionfromcovid.databinding.FragmentRedZoneBinding

/**
 * A simple [Fragment] subclass.
 */
class RedZoneFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentRedZoneBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_red_zone, container, false)
        binding.home.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_redZoneFragment_to_titleFragment)
        }
        binding.helpline.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_redZoneFragment_to_helplineFragment)
        }
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = "Result"
        return binding.root
    }

    private fun getShareIntent() : Intent? {
        return activity?.let {
            ShareCompat.IntentBuilder.from(it)
                .setText(getString(R.string.share_red_text))
                .setType("text/plain")
                .intent
        }
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.safe, menu)
        if (null == getShareIntent()?.resolveActivity(activity!!.packageManager)) {
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
