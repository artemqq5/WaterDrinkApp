package ppatsrrif.one.waterstate.presentation.login.activity

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    private var timeBackPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    override fun onResume() {
        super.onResume()
        onBackPressedDispatcher.addCallback {
            if (timeBackPressed + 1000 > System.currentTimeMillis()) {
                finishAffinity()
            } else {
                Snackbar.make(
                    findViewById(R.id.fragmentContainerLogin),
                    resources.getString(R.string.toast_exit),
                    Snackbar.LENGTH_SHORT
                ).show()

                timeBackPressed = System.currentTimeMillis()
            }
        }
    }
}