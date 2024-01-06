package ppatsrrif.one.waterstate.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.repository.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.ActivityLoginBinding
import ppatsrrif.one.waterstate.presentation.home.MainActivity
import ppatsrrif.one.waterstate.domain.CompareDates
import ppatsrrif.one.waterstate.domain.DateHelper
import ppatsrrif.one.waterstate.presentation.viewModel.ViewModelItem

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val sharedPreferencesHelper by lazy {
        SharedPreferencesHelper(this)
    }
    private var timeBackPressed: Long = 0

    private val dateHelper by lazy {
        DateHelper()
    }

    private val dateCheck by lazy {
        CompareDates(
            sharedPreferencesHelper,
            ViewModelProvider(this)[ViewModelItem::class.java]
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    // launch some fragment
    fun nextFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container, fragment
        ).commit()
    }

    // launch start MainActivity
    fun startMainAct() {
        sharedPreferencesHelper.setStartMode(1)
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onBackPressed() {
        if (timeBackPressed + 1000 > System.currentTimeMillis()) {
            finishAffinity()
        } else timeBackPressed = System.currentTimeMillis()
    }


    override fun onResume() {
        super.onResume()

        dateCheck.checkWeek(dateHelper.getWeek())
    }
}