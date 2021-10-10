package ppatsrrif.one.waterstate.mainPart.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.ActivityMainBinding
import ppatsrrif.one.waterstate.mainPart.fragments.FragmentHome
import ppatsrrif.one.waterstate.mainPart.fragments.FragmentProfile
import ppatsrrif.one.waterstate.mainPart.fragments.FragmentSettings
import ppatsrrif.one.waterstate.mainPart.helperClasses.CompareDates
import ppatsrrif.one.waterstate.mainPart.helperClasses.DateHelper
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem

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

        setTheme(R.style.Theme_WaterState)


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

    override fun onBackPressed() {

        if (timeBackPressed + 1000 > System.currentTimeMillis()) {
            finishAffinity()
        } else timeBackPressed = System.currentTimeMillis()

    }

    override fun onResume() {
        super.onResume()

        dateCheck.checkWeek(dateHelper.getWeek())
        viewModelItem.date.value = dateHelper.getDay()
    }


}