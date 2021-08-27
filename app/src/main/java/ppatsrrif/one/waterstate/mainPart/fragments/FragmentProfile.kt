package ppatsrrif.one.waterstate.mainPart.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.SplashScreen
import ppatsrrif.one.waterstate.databinding.FragmentProfileBinding

class FragmentProfile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater)

        // initializing sharedPreferenceHelper
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

        // set data to profile info
        binding.nameText.text = sharedPreferencesHelper.getUserName()
        binding.weightText.text = "${sharedPreferencesHelper.getUserWeight()} кг"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.resetProfile.setOnClickListener {
           sharedPreferencesHelper.setStartMode(0)
           startActivity(Intent(requireContext(), SplashScreen::class.java))
        }

    }

    companion object {

        fun newInstance() = FragmentProfile()
    }
}