package ppatsrrif.one.waterstate.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.repository.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.ActivityMainBinding
import ppatsrrif.one.waterstate.domain.CompareDates
import ppatsrrif.one.waterstate.domain.DateHelper
import ppatsrrif.one.waterstate.presentation.viewModel.ViewModelItem

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var timeBackPressed: Long = 0

    private val dateHelper by lazy {
        DateHelper()
    }

    private val viewModelItem by lazy {
        ViewModelProvider(this)[ViewModelItem::class.java]
    }

    private val dateCheck by lazy {
        CompareDates(
            SharedPreferencesHelper(this),
            viewModelItem
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // set action for navigation items
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.home -> nextFragment(FragmentHome.newInstance())
                R.id.profile -> nextFragment(FragmentProfile.newInstance())
                R.id.settings -> nextFragment(FragmentSettings.newInstance())

                else -> false
            }
        }

    }

    // launch some fragment
    private fun nextFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragmentContainerView, fragment
        ).commit()

        return true
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

        if (timeBackPressed + 2000 > System.currentTimeMillis()) {
            finishAffinity()
        } else {
            timeBackPressed = System.currentTimeMillis()
            Toast.makeText(
                this, resources.getString(R.string.toast_exit),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onResume() {
        super.onResume()

        dateCheck.checkWeek(dateHelper.getWeek())
        viewModelItem.date.value = dateHelper.getDay()
    }


}