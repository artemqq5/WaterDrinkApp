package ppatsrrif.one.waterstate.presentation.home.activity

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.ApplicationStart.Companion.log
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.ActivityMainBinding
import ppatsrrif.one.waterstate.domain.usecase.AdsUseCase
import ppatsrrif.one.waterstate.domain.usecase.AdsUseCase.Companion.ID_INTERSTITIAL
import ppatsrrif.one.waterstate.domain.usecase.AdsUseCase.Companion.stateShowed

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment

    private var timeBackPressed: Long = 0
    private var mInterstitialAd: InterstitialAd? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadAds()

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerMain) as NavHostFragment

//        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragmentHome -> {
                    navHostFragment.navController.navigate(R.id.fragmentHome)
                    true
                }

                R.id.fragmentProfile -> {
                    navHostFragment.navController.navigate(R.id.fragmentProfile)
                    true
                }

                R.id.fragmentSettings -> {
                    navHostFragment.navController.navigate(R.id.fragmentSettings)
                    true
                }

                else -> false
            }
        }



    }

    override fun onResume() {
        super.onResume()
        onBackPressedDispatcher.addCallback {
            if (timeBackPressed + 1000 > System.currentTimeMillis()) {
                finishAffinity()
            } else {
                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.toast_exit),
                    Snackbar.LENGTH_SHORT
                ).show()

                timeBackPressed = System.currentTimeMillis()
            }
        }

    }

    private fun loadAds() {

        if (stateShowed)
            return

        val adRequest = AdRequest.Builder().build()

        binding.backLoaderAds.visibility = View.VISIBLE

        InterstitialAd.load(
            this,
            ID_INTERSTITIAL,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                log(adError.toString())
                    mInterstitialAd = null
                    binding.backLoaderAds.visibility = View.INVISIBLE
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                log("Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    stateShowed = true
                    mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                        override fun onAdClicked() {
                            // Called when a click is recorded for an ad.
                            log("Ad was clicked.")
                        }

                        override fun onAdDismissedFullScreenContent() {
                            // Called when ad is dismissed.
                            log("Ad dismissed fullscreen content.")
                            mInterstitialAd = null
                        }

                        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                            // Called when ad fails to show.
                            log("Ad failed to show fullscreen content.")
                            mInterstitialAd = null
                            binding.backLoaderAds.visibility = View.INVISIBLE
                        }

                        override fun onAdImpression() {
                            // Called when an impression is recorded for an ad.
                            log("Ad recorded an impression.")
                        }

                        override fun onAdShowedFullScreenContent() {
                            // Called when ad is shown.
                            log("Ad showed fullscreen content.")
                            binding.backLoaderAds.visibility = View.INVISIBLE

                        }
                    }

                    mInterstitialAd?.show(this@MainActivity)
                }
            })



    }


}