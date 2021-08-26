package ppatsrrif.one.waterstate.loginUser.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ppatsrrif.one.waterstate.databinding.FragmentWeightBinding
import ppatsrrif.one.waterstate.loginUser.activity.LoginActivity

class FragmentWeight : Fragment() {

    private lateinit var preferenceWeight: SharedPreferences
    private lateinit var binding: FragmentWeightBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        preferenceWeight = requireContext().getSharedPreferences(LoginActivity.PREFERENCE_WEIGHT_USER,
            AppCompatActivity.MODE_PRIVATE
        )

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
            preferenceWeight.edit().putInt(LoginActivity.PREFERENCE_WEIGHT_USER,
                binding.weightChoice.value).apply()

            //run method from LoginActivity
            (activity as LoginActivity).nextFragment(FragmentEnd.newInstance())
        }
    }

    companion object {
        fun newInstance() = FragmentWeight()
    }
}