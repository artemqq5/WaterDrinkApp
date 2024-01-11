package ppatsrrif.one.waterstate.presentation.more_stats.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.databinding.ActivityMoreStatsBinding

@AndroidEntryPoint
class MoreStatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoreStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }



}