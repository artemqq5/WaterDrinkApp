package ppatsrrif.one.waterstate.mainPart.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.FragmentHomeBinding
import ppatsrrif.one.waterstate.mainPart.activity.MoreStats
import ppatsrrif.one.waterstate.mainPart.dialogs.DialogAddWater
import ppatsrrif.one.waterstate.mainPart.dialogs.DialogListItemWater
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelUser
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round

class FragmentHome : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private val liveDataUser: ViewModelUser by activityViewModels()
    private val viewModelItem: ViewModelItem by lazy {
        ViewModelProvider(requireActivity())[ViewModelItem::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)

        // initializing SharedPreferencesHelper
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

        binding.floatingActionButton.setOnClickListener(this)
        binding.moreStatistic.setOnClickListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // get status WHO Recommendation
        val statusVisibilityRecommendation = sharedPreferencesHelper.getStatusRecommendation()

        // set default status WHO Recommendation
        binding.blockRecommendation.visibility =
            if (statusVisibilityRecommendation) View.VISIBLE else View.INVISIBLE

        // set water norm
        liveDataUser.liveDataWeight.observe(viewLifecycleOwner) {
            binding.waterNorma.text =
                resources.getString(R.string.sub_text_home1) + " " + normalWater(it) + " " + resources.getString(R.string.sub_text_home2)

        }

        viewModelItem.date.observe(viewLifecycleOwner) { nowDate ->

            // set all volume drunk water for a day
            viewModelItem.listSomeDay(nowDate).observe(viewLifecycleOwner, { listItems ->
                var sum = 0.0

                for (item in listItems) {
                    sum += item.volumeWater
                }


                binding.countWater.text = addWater(sum).toString()
            })

        }


        // close WHO Recommendation
        binding.closeRecommendation.setOnClickListener {
            binding.blockRecommendation.visibility = View.INVISIBLE

            sharedPreferencesHelper.setStatusRecommendation(false)
        }

        // start WHO site
        binding.buttonMore.setOnClickListener {
            val uriWho =
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.who.int/news-room/fact-sheets/detail/drinking-water")
                )
            startActivity(uriWho)
        }

        // open list water item
        binding.buttonMoreDrunk.setOnClickListener {

            val dialog = DialogListItemWater()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction
                .add(android.R.id.content, dialog)
                .addToBackStack(null)
                .commit()
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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.floating_action_button -> {
                DialogAddWater().show(parentFragmentManager, "DialogAddWater")
            }

            R.id.more_statistic -> {
                startActivity(
                    Intent(requireContext(), MoreStats::class.java)
                )

            }
        }
    }

    // add volume to UI
    private fun addWater(volume: Double): Double {

        return round((volume / 1000.0) * 10) / 10.0

    }
}