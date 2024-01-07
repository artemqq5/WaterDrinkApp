package ppatsrrif.one.waterstate

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationStart : Application() {
    companion object {
        fun log(any: Any?) {
            Log.i("MyLog", "$any")
        }
    }
}