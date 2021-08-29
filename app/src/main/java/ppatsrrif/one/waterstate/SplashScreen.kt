package ppatsrrif.one.waterstate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ppatsrrif.one.waterstate.loginUser.activity.LoginActivity
import ppatsrrif.one.waterstate.mainPart.activity.MainActivity

class SplashScreen : AppCompatActivity() {

    // for checking condition
    private var stateLoading = 0

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        // initializing sharedPreferenceHelper
        sharedPreferencesHelper = SharedPreferencesHelper(this)

        // get actually value
        stateLoading = sharedPreferencesHelper.getStartMode()

        // choice activity to start
        when(stateLoading) {
            1 -> startActivity(Intent(this, MainActivity::class.java))
            else -> startActivity(Intent(this, LoginActivity::class.java))
        }

        finish()
    }

}