package ppatsrrif.one.waterstate.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ppatsrrif.one.waterstate.presentation.home.MainActivity
import ppatsrrif.one.waterstate.domain.CompareDates
import ppatsrrif.one.waterstate.domain.DateHelper
import ppatsrrif.one.waterstate.repository.database.TableIItemStorageGoals
import ppatsrrif.one.waterstate.presentation.viewModel.ViewModelItem
import ppatsrrif.one.waterstate.repository.SharedPreferencesHelper

class SplashScreen : AppCompatActivity() {

    // for checking condition
    private var stateLoading = 0

    private val dateHelper by lazy {
        DateHelper()
    }

    private val sharedPreferencesHelper by lazy {
        SharedPreferencesHelper(this)
    }

    private val viewModelItem by lazy {
        ViewModelProvider(this)[ViewModelItem::class.java]
    }

    private val checkDateDB by lazy {
        CompareDates(sharedPreferencesHelper, viewModelItem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get actually value
        stateLoading = sharedPreferencesHelper.getStartMode()

        // create table elements if not exists (ONCE)
        for (n in 1..7) {
            viewModelItem.addGoals(TableIItemStorageGoals(n, 0))
        }

        checkDateDB.checkWeek(dateHelper.getWeek())

        // choice activity to start
        when (stateLoading) {
            1 -> startActivity(Intent(this, MainActivity::class.java))
            else -> startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}