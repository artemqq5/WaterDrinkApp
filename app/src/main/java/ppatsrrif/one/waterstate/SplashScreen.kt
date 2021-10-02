package ppatsrrif.one.waterstate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ppatsrrif.one.waterstate.loginUser.activity.LoginActivity
import ppatsrrif.one.waterstate.mainPart.activity.MainActivity
import ppatsrrif.one.waterstate.mainPart.roomDataBase.TableIItemStorageGoals
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem

class SplashScreen : AppCompatActivity() {

    // for checking condition
    private var stateLoading = 0
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private val viewModelItem by lazy {
        ViewModelProvider(this)[ViewModelItem::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        // initializing sharedPreferenceHelper
        sharedPreferencesHelper = SharedPreferencesHelper(this)

        // get actually value
        stateLoading = sharedPreferencesHelper.getStartMode()

        for(n in 1..7) {
            viewModelItem.addGoals(TableIItemStorageGoals(n, 0))
        }

        // choice activity to start
        when(stateLoading) {
            1 -> startActivity(Intent(this, MainActivity::class.java))
            else -> startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}