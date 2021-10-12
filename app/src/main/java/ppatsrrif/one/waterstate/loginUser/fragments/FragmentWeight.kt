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
    private val sharedPreferencesHelper by lazy {
        SharedPreferencesHelper(requireContext())
    }
    private val weightKey1 = "WeightUserKg"
    private val weightKey2 = "WeightUserGr"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWeightBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //set params for numberPicker
        binding.weightChoice.apply {
            maxValue = 150
            minValue = 30
        }
        binding.weightChoiceSub.apply {
            maxValue = 9
            minValue = 0
        }

        // next button
        binding.buttonNextEnd.setOnClickListener {

            val weight = binding.weightChoice.value + (binding.weightChoiceSub.value / 10.0f)

            //save user weight
            sharedPreferencesHelper.setUserWeight(weight)

            //run method from LoginActivity
            (activity as LoginActivity).nextFragment(FragmentEnd.newInstance())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {

            putInt(weightKey1, binding.weightChoice.value)
            putInt(weightKey2, binding.weightChoiceSub.value)
        }

        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {

        binding.weightChoice.value = savedInstanceState?.getInt(weightKey1, 30) ?: 30
        binding.weightChoiceSub.value = savedInstanceState?.getInt(weightKey2, 0) ?: 0
        super.onViewStateRestored(savedInstanceState)
    }

    companion object {
        fun newInstance() = FragmentWeight()
    }
}