package ppatsrrif.one.waterstate

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ppatsrrif.one.waterstate.loginUser.activity.LoginActivity
import ppatsrrif.one.waterstate.mainPart.activity.MainActivity

class SplashScreen : AppCompatActivity() {

    // for checking condition
    private var stateLoading = 0

    // variables for check on pass startActivity
    private lateinit var preferenceLoading: SharedPreferences
    private val namePreference = "checkToPass"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        // declaring sharedPreference
        preferenceLoading = getSharedPreferences(namePreference, MODE_PRIVATE)

        // get actually value
        stateLoading = preferenceLoading.getInt(namePreference, 0)

        // choice activity to start
        when(stateLoading) {
            0 -> startActivity(Intent(this, MainActivity::class.java))
            else -> startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    fun setStateLoading() {
        preferenceLoading.edit().putInt(namePreference, 1).apply()
    }

}