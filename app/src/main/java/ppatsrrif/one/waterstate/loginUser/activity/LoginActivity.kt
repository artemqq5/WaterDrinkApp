package ppatsrrif.one.waterstate.loginUser.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.ActivityLoginBinding
import ppatsrrif.one.waterstate.mainPart.activity.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initializing sharedPreferenceHelper
        sharedPreferencesHelper = SharedPreferencesHelper(this)

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

}