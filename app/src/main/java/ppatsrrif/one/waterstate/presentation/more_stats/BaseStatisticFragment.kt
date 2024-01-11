package ppatsrrif.one.waterstate.presentation.more_stats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.FragmentBaseStatisticBinding
import ppatsrrif.one.waterstate.presentation.home.activity.MainActivity

class BaseStatisticFragment : Fragment() {

    private lateinit var binding: FragmentBaseStatisticBinding

    private val listOfIdImageStatus =
        arrayOf(R.id.Mon, R.id.Tue, R.id.Wed, R.id.Thu, R.id.Fri, R.id.Sat, R.id.Sun)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBaseStatisticBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // todo додати google ads
        // додати функціонал, наповнити даними, mvvm

        // ads
//        val bannerView = AdView(baseContext)
//        binding.adViewContainer.addView(bannerView)
//        KeysAds().run {
//            loadBanner(
//                bannerView, KeysAds.justBannerKey
//            )
//        }

        binding.topAppBar.setNavigationOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }



    }

}