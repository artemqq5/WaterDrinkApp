package ppatsrrif.one.waterstate.mainPart.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ppatsrrif.one.waterstate.databinding.FragmentHomeBinding
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelUser
import java.math.RoundingMode
import java.text.DecimalFormat

class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val liveDataUser: ViewModelUser by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // set water norm
        liveDataUser.liveDataWeight.observe(requireActivity()) {

            binding.waterNorma.text = "составляет ${normalWater(it)}" +
                    " литра, в соответсвии с расчетом 35 милилитров на 1 кллограм массы тела"
        }

    }

    companion object {

        fun newInstance() = FragmentHome()
    }

    // calculate water norm
    private fun normalWater(kg: Float): String {

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format((kg * 35.0) / 1000.0)
    }
}