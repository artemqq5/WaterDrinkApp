package ppatsrrif.one.waterstate.presentation.home

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
import ppatsrrif.one.waterstate.repository.storage.UserUserStoragePreference
import ppatsrrif.one.waterstate.databinding.FragmentHomeBinding
import ppatsrrif.one.waterstate.domain.TranslateVolume
import ppatsrrif.one.waterstate.presentation.home.activity.MoreStatsActivity
import ppatsrrif.one.waterstate.presentation.viewModel.ViewModelItem
import ppatsrrif.one.waterstate.presentation.viewModel.ViewModelUser


class FragmentHome : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val userStoragePreference by lazy {
        UserUserStoragePreference(requireContext())
    }
    private val liveDataUser: ViewModelUser by activityViewModels()
    private val viewModelItem: ViewModelItem by lazy {
        ViewModelProvider(requireActivity())[ViewModelItem::class.java]
    }

    private val translateVolume by lazy {
        TranslateVolume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)

        binding.floatingActionButton.setOnClickListener(this)
        binding.moreStatistic.setOnClickListener(this)
        binding.buttonMoreDrunk.setOnClickListener(this)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // ads
//        val bannerView = AdView(requireContext())
//        binding.adViewContainer.addView(bannerView)
//        KeysAds().run {
//            loadBanner(
//                bannerView, KeysAds.justBannerKey,
//            )
//        }


//        // get status WHO Recommendation
//        val statusVisibilityRecommendation = sharedPreferencesHelper.getStatusRecommendation()
//
//        // set default status WHO Recommendation
//        binding.blockRecommendation.visibility =
//            if (statusVisibilityRecommendation) View.VISIBLE else View.INVISIBLE
//
//        // set water norm
//        liveDataUser.liveDataWeight.observe(viewLifecycleOwner) {
//            binding.waterNorma.text =
//                resources.getString(R.string.sub_text_home1) + " " + translateVolume.normalWaterUI(
//                    it,
//                    1
//                ) + " " + resources.getString(R.string.sub_text_home2)
//
//        }



        viewModelItem.date.observe(viewLifecycleOwner) { nowDate ->

//            // set all volume drunk water for a day
//            viewModelItem.listSomeDay(nowDate).observe(viewLifecycleOwner
//            ) { listItems ->
//                var sum = 0.0
//
//                for (item in listItems) {
//                    sum += item.volumeWater
//                }
//
//
//                binding.countWater.text = translateVolume.addWater(sum, 1).toString()
//
//                Log.i("sdfe34f234f1", "$sum - ${translateVolume.addWater(sum, 1)}")
//            }

        }


        // close WHO Recommendation
        binding.closeRecommendation.setOnClickListener(this)
        // start WHO site
        binding.buttonMore.setOnClickListener(this)


    }

    companion object {

        fun newInstance() = FragmentHome()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.floating_action_button -> {
                DialogAddWater().show(parentFragmentManager, "DialogAddWater")
            }

            R.id.more_statistic -> {
                startActivity(
                    Intent(requireContext(), MoreStatsActivity::class.java)
                )

            }

            R.id.closeRecommendation -> {
                binding.blockRecommendation.visibility = View.INVISIBLE

//                sharedPreferencesHelper.setStatusRecommendation(false)
            }

            R.id.button_more -> {
                val uriWho =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.who.int/news-room/fact-sheets/detail/drinking-water")
                    )
                startActivity(uriWho)
            }

            R.id.buttonMoreDrunk -> {
                val dialog = DialogListItemWater()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                transaction
                    .add(android.R.id.content, dialog)
                    .addToBackStack(null)
                    .commit()



                dialog.isCancelable = true
            }
        }
    }


}


