package com.se_prashant.preventionfromcovid

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.se_prashant.preventionfromcovid.databinding.ActivityMainBinding
import com.se_prashant.preventionfromcovid.databinding.FragmentGreenZoneBinding

/**
 * A simple [Fragment] subclass.
 */
class GreenZoneFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentGreenZoneBinding= DataBindingUtil.inflate(
            inflater, R.layout.fragment_green_zone, container, false)
      binding.home.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_greenZoneFragment_to_titleFragment2)
        }

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = "Result"
        return binding.root
    }

    private fun getShareIntent() : Intent? {
        return activity?.let {
            ShareCompat.IntentBuilder.from(it)
                .setText(getString(R.string.share_success_text))
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
