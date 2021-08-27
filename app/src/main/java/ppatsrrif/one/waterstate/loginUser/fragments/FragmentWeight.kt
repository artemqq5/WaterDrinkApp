package ppatsrrif.one.waterstate.loginUser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.FragmentWeightBinding
import ppatsrrif.one.waterstate.loginUser.activity.LoginActivity

class FragmentWeight : Fragment() {

    private lateinit var binding: FragmentWeightBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // initializing sharedPreferenceHelper
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

        binding = FragmentWeightBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //set params for numberPicker
        binding.weightChoice.apply {
            maxValue = 150
            minValue = 30
        }

        binding.buttonNextEnd.setOnClickListener {

            //save user weight
            sharedPreferencesHelper.setUserWeight(binding.weightChoice.value.toString())

            //run method from LoginActivity
            (activity as LoginActivity).nextFragment(FragmentEnd.newInstance())
        }
    }

    companion object {
        fun newInstance() = FragmentWeight()
    }
}