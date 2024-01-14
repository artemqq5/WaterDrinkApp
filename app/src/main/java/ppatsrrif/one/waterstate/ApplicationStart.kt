package ppatsrrif.one.waterstate

import android.app.Application
import android.util.Log
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationStart : Application() {
    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) {}

    }

    companion object {
        fun log(any: Any?) {
            Log.i("MyLog", "$any")
        }
    }
}