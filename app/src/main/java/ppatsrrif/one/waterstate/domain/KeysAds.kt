package ppatsrrif.one.waterstate.domain

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.widget.FrameLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class KeysAds {

    fun adSize(
        activity: Activity,
        container: FrameLayout,
        context: Context,
        margins: Int = 0
    ): AdSize {
        val display = activity.windowManager?.defaultDisplay
        val outMetrics = DisplayMetrics()
        display?.getMetrics(outMetrics)

        val density = outMetrics.density

        var adWidthPixels = container.width.toFloat()
        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }

        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth - margins)
    }

    fun loadBanner(adView: AdView, key: String) {
        adView.adUnitId = key
        val adRequest = AdRequest.Builder().build()

        adView.loadAd(adRequest)
    }

    companion object {


        const val justBannerKey = "ca-app-pub-3940256099942544/6300978111"

    }

}