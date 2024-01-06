package ppatsrrif.one.waterstate

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp
import ppatsrrif.one.waterstate.repository.Repository

@HiltAndroidApp
class ApplicationStart : Application() {

    override fun onCreate() {
        super.onCreate()

        Repository.initialize(this)
//        MobileAds.initialize(this) {}
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}