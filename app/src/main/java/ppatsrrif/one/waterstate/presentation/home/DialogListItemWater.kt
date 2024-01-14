package ppatsrrif.one.waterstate.presentation.home

import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.databinding.DialogListItemWaterBinding
import ppatsrrif.one.waterstate.domain.usecase.AdsUseCase.Companion.ID_BANNER
import ppatsrrif.one.waterstate.presentation.home.recyclerView.AdapterListItemWater
import ppatsrrif.one.waterstate.presentation.viewmodel.WaterViewModel


@AndroidEntryPoint
class DialogListItemWater : DialogFragment() {

    private lateinit var bindingDialog: DialogListItemWaterBinding

    private val adapterRecycler by lazy {
        AdapterListItemWater {}
    }

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

            var adWidthPixels = bindingDialog.adViewContainer.width.toFloat()

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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // initializing binding
        bindingDialog = DialogListItemWaterBinding.inflate(inflater)

        return bindingDialog.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // set params for dialog
        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }

        adView = AdView(requireContext())
        bindingDialog.adViewContainer.addView(adView)

        bindingDialog.adViewContainer.viewTreeObserver.addOnGlobalLayoutListener {
            if (!initialLayoutComplete) {
                initialLayoutComplete = true
                loadBanner()

            }
        }

        bindingDialog.recyclerItemWater.adapter = adapterRecycler

        adapterRecycler.listener = {
            waterViewModel.deleteWater(it)
        }

        waterViewModel.listCurrentWater.observe(viewLifecycleOwner) {
            adapterRecycler.setNewList(it)
        }

        // button close dialog
        bindingDialog.closeDialogButton.setOnClickListener {
            dismiss()
        }


    }

    private fun loadBanner() {
        adView?.let { adView ->
            adView.adUnitId = ID_BANNER

            adView.setAdSize(adSize)

            val adRequest = AdRequest.Builder().build()

            adView.loadAd(adRequest)
        }

    }

}