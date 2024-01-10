package ppatsrrif.one.waterstate.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.FragmentHomeBinding
import ppatsrrif.one.waterstate.domain.repository.UserRepository
import javax.inject.Inject

@AndroidEntryPoint
class FragmentHome : Fragment(), View.OnClickListener {

    private val recommendationWHO =
        "https://www.who.int/news-room/fact-sheets/detail/drinking-water"

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var userRepositoryImp: UserRepository

//    private val userStoragePreference by lazy {
//        UserUserStoragePreference(requireContext())
//    }
//    private val liveDataUser: ViewModelUser by activityViewModels()
//    private val viewModelItem: ViewModelItem by lazy {
//        ViewModelProvider(requireActivity())[ViewModelItem::class.java]
//    }
//
//    private val translateVolume by lazy {
//        TranslateVolume()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.floatingActionButton.setOnClickListener(this)
        binding.moreStatistic.setOnClickListener(this)
        binding.buttonMoreDrunk.setOnClickListener(this)
        binding.closeRecommendation.setOnClickListener(this)
        binding.buttonMore.setOnClickListener(this)

        // ads
//        val bannerView = AdView(requireContext())
//        binding.adViewContainer.addView(bannerView)
//        KeysAds().run {
//            loadBanner(
//                bannerView, KeysAds.justBannerKey,
//            )
//        }


        // set default status WHO Recommendation
        binding.recommendation.visibility =
            if (userRepositoryImp.getStatusRecommendation()) View.VISIBLE else View.INVISIBLE
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


//        viewModelItem.date.observe(viewLifecycleOwner) { nowDate ->

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

//        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.floating_action_button -> {
                findNavController().navigate(R.id.action_fragmentHome_to_dialogAddWater)
            }

            R.id.more_statistic -> {
                findNavController().navigate(R.id.action_fragmentHome_to_moreStatsActivity)
            }

            R.id.closeRecommendation -> {
                binding.recommendation.visibility = View.INVISIBLE
                userRepositoryImp.setStatusRecommendation(false)
            }

            R.id.button_more -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(recommendationWHO)))
            }

            R.id.buttonMoreDrunk -> {
                findNavController().navigate(R.id.action_fragmentHome_to_dialogListItemWater)
            }
        }
    }


}


