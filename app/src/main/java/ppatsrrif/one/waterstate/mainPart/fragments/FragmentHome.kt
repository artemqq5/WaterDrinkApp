package ppatsrrif.one.waterstate.mainPart.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.FragmentHomeBinding
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelUser
import java.math.RoundingMode
import java.text.DecimalFormat

class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private val liveDataUser: ViewModelUser by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)

        // initializing SharedPreferencesHelper
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // get status WHO Recommendation
        val statusVisibilityRecommendation = sharedPreferencesHelper.getStatusRecommendation()

        // set default status WHO Recommendation
        binding.blockRecommendation.visibility =
            if(statusVisibilityRecommendation) View.VISIBLE else View.INVISIBLE

        // set water norm
        liveDataUser.liveDataWeight.observe(requireActivity()) {
            binding.waterNorma.text = "составляет ${normalWater(it)}" +
                    " литра, в соответсвии с расчетом 35 милилитров на 1 кллограм массы тела"
        }

        // close WHO Recommendation
        binding.closeRecommendation.setOnClickListener {
            binding.blockRecommendation.visibility = View.INVISIBLE

            sharedPreferencesHelper.setStatusRecommendation(false)
        }

        // start WHO site
        binding.buttonMore.setOnClickListener {
            val uriWho =
                Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.who.int/news-room/fact-sheets/detail/drinking-water")
                )
            startActivity(uriWho)
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