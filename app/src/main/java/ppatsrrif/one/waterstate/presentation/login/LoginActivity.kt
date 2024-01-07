package ppatsrrif.one.waterstate.presentation.login

import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    private var timeBackPressed: Long = 0

    override fun onResume() {
        super.onResume()
        onBackPressedDispatcher.addCallback {
            if (timeBackPressed + 1000 > System.currentTimeMillis()) {
                finishAffinity()
            } else {
                Snackbar.make(
                    findViewById(R.id.fragment_container_login),
                    resources.getString(R.string.toast_exit),
                    Snackbar.LENGTH_SHORT
                ).show()

                timeBackPressed = System.currentTimeMillis()
            }
        }
    }
}