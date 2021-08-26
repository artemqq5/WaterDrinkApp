package ppatsrrif.one.waterstate.loginUser.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ppatsrrif.one.waterstate.mainPart.activity.MainActivity
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.SplashScreen
import ppatsrrif.one.waterstate.databinding.ActivityLoginBinding
import ppatsrrif.one.waterstate.loginUser.fragments.FragmentEnd

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
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
       SplashScreen().setStateLoading()
       startActivity(Intent(this, MainActivity::class.java))
    }

    companion object {
        const val PREFERENCE_NAME_USER = "name_user"
        const val PREFERENCE_WEIGHT_USER = "weight_user"
    }
}