package ppatsrrif.one.waterstate.mainPart.roomDataBase.runApp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import ppatsrrif.one.waterstate.mainPart.roomDataBase.Repository

class ApplicationStartActions : Application() {

    override fun onCreate() {
        super.onCreate()

        Repository.initialize(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}