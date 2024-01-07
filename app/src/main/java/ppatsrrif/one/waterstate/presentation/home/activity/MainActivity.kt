package ppatsrrif.one.waterstate.presentation.home.activity

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment

    private var timeBackPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerMain) as NavHostFragment

        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)

    }


    override fun onResume() {
        super.onResume()
        onBackPressedDispatcher.addCallback {
            if (timeBackPressed + 1000 > System.currentTimeMillis()) {
                finishAffinity()
            } else {
                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.toast_exit),
                    Snackbar.LENGTH_SHORT
                ).show()

                timeBackPressed = System.currentTimeMillis()
            }
        }

    }


}