package ppatsrrif.one.waterstate.mainPart.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.ActivityMainBinding
import ppatsrrif.one.waterstate.mainPart.fragments.FragmentHome
import ppatsrrif.one.waterstate.mainPart.fragments.FragmentProfile
import ppatsrrif.one.waterstate.mainPart.fragments.FragmentSettings

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set action for navigation items
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {

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


}