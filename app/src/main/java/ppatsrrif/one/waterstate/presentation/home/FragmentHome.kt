package ppatsrrif.one.waterstate.presentation.home

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.interstitial.InterstitialAd
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.ApplicationStart.Companion.log
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.FragmentHomeBinding
import ppatsrrif.one.waterstate.domain.usecase.AdsUseCase
import ppatsrrif.one.waterstate.domain.usecase.VolumeUseCase
import ppatsrrif.one.waterstate.presentation.viewmodel.UserViewModel
import ppatsrrif.one.waterstate.presentation.viewmodel.WaterViewModel
import javax.inject.Inject

@AndroidEntryPoint
class FragmentHome : Fragment(), View.OnClickListener {

    private val recommendationWHO =
        "https://www.who.int/news-room/fact-sheets/detail/drinking-water"

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var volumeUseCase: VolumeUseCase

    private val userViewModel: UserViewModel by activityViewModels()

    private val waterViewModel: WaterViewModel by activityViewModels()

    private var adView: AdView? = null
    private var initialLayoutComplete = false

    private val adSize: AdSize
        get() {
            val bounds: Rect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                requireActivity().windowManager.currentWindowMetrics.bounds
            } else {
                val displayMetrics = DisplayMetrics()
                @Suppress("DEPRECATION")
                requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
                Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
            }

            var adWidthPixels = binding.adViewContainer.width.toFloat()

            if (adWidthPixels == 0f) {
                adWidthPixels = bounds.width().toFloat()
            }

            val density = resources.displayMetrics.density
            val adWidth = (adWidthPixels / density).toInt()

            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                requireContext(),
                adWidth
            )
        }

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
        adView = AdView(requireContext())
        binding.adViewContainer.addView(adView)

        binding.adViewContainer.viewTreeObserver.addOnGlobalLayoutListener {
            if (!initialLayoutComplete) {
                initialLayoutComplete = true
                loadBanner()
            }
        }

        userViewModel.liveDataUser.observe(viewLifecycleOwner) { user ->
            val waterConsumption = volumeUseCase.waterAlgorithm(user)
            binding.waterConsumption.text =
                resources.getString(
                    R.string.water_consumption,
                    volumeUseCase.millilitersToLiters(waterConsumption).toString()
                )
        }

        // set default status WHO Recommendation
        binding.recommendation.visibility =
            if (userViewModel.getDefaultStatusRecommendation()) View.VISIBLE else View.INVISIBLE


        waterViewModel.listCurrentWater.observe(viewLifecycleOwner) { listWater ->
            val sumWater = listWater.sumOf { it.volumeWater }
            binding.countWater.text = resources.getString(
                R.string.sum_liters,
                volumeUseCase.millilitersToLiters(sumWater).toString()
            )
        }

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
                userViewModel.setStatusRecommendation(false)
            }

            R.id.button_more -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(recommendationWHO)))
            }

            R.id.buttonMoreDrunk -> {
                findNavController().navigate(R.id.action_fragmentHome_to_dialogListItemWater)
            }
        }
    }


    private fun loadBanner() {
        adView?.let { adView ->
            adView.adUnitId = AdsUseCase.ID_BANNER
            adView.setAdSize(adSize)
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        }

    }


}


