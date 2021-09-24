package ppatsrrif.one.waterstate.mainPart.roomDataBase.runApp

import android.app.Application
import ppatsrrif.one.waterstate.mainPart.roomDataBase.Repository

class ApplicationStartActions: Application() {

    override fun onCreate() {
        super.onCreate()

        Repository.initialize(this)
    }
}