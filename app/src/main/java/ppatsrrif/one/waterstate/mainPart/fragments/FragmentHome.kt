package ppatsrrif.one.waterstate.mainPart.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.FragmentHomeBinding

class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)

        // initializing sharedPreferenceHelper
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

        // calculate water norm
        val normalWater = (sharedPreferencesHelper.getUserWeight() * 35) / 1000

        // set water norm
        binding.waterNorma.text = "составляет $normalWater литра, в соответсвии с расчетом 35 милилитров на 1 кллограм массы тела"



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



    }

    companion object {

        fun newInstance() = FragmentHome()
    }
}