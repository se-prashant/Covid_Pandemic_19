package com.se_prashant.preventionfromcovid

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.gson.Gson
import com.se_prashant.preventionfromcovid.databinding.FragmentTitleBinding
import kotlinx.android.synthetic.main.fragment_title.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

import androidx.appcompat.app.AppCompatActivity

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title,container,false)
        setHasOptionsMenu(true)
        binding.indiaUpdate.setOnClickListener{view:View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_mainActivity2)
        }
        binding.playButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_selfAssesFragment)
        }
        binding.prevention.setOnClickListener{view:View->
            view.findNavController().navigate(R.id.action_titleFragment_to_checkSymptom)
        }
        binding.covidHelpline.setOnClickListener{view :View->
            view.findNavController().navigate(R.id.action_titleFragment_to_helplineFragment)
        }
        fetchResults()
        return binding.root
    }

    private fun fetchResults() {

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url("https://disease.sh/v2/all")
            .build()

        val api = okHttpClient

        api.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("unreax@#@#")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = Gson().fromJson(
                    response.body?.string(),
                    ResponseWorld::class.java
                )

               activity!!.runOnUiThread{
                    confirmedTv?.text = data.cases.toString()
                    recoveredTv?.text = data.recovered.toString()
                    activeTv?.text = data.active.toString()
                    deceasedTv?.text = data.deaths.toString()
                }
            }

        })
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,view!!.findNavController()) ||super.onOptionsItemSelected(item)
    }

}
