package ppatsrrif.one.waterstate.mainPart.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ppatsrrif.one.waterstate.databinding.ActivityMoreStatsBinding
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelUser
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.floor

class MoreStats : AppCompatActivity() {

    private lateinit var binding: ActivityMoreStatsBinding
    private val viewModelItem: ViewModelItem by lazy {
        ViewModelProvider(this)[ViewModelItem::class.java]
    }
    private val liveDataUser: ViewModelUser by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }

        // set max progress
        liveDataUser.liveDataWeight.observe(this) {
            binding.textPart3.text = normalWater(it)
            binding.textPart3Week.text = (normalWater(it).toDouble() * 7).toString()

            binding.progressDrink.max = floor(normalWater(it).toFloat() * 100).toInt()
            binding.progressDrinkWeek.max = floor((normalWater(it).toFloat() * 100) * 7).toInt()

        }

        // set progress for Day
        viewModelItem.listWaterItem.observe(this) {
            var sum = 0.0

            for (item in it) {
                sum += item.volumeWater
            }

            // reg for volume
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            df.format(sum)

            binding.textPart1.text = (df.format(sum).toDouble()).toString()

            if (binding.textPart1.text == binding.textPart3.text) {
                binding.progressDrink.progress = binding.progressDrink.max

            } else {
                binding.progressDrink.progress = floor(sum * 100).toInt()

            }

        }

        // set progress for Week
        viewModelItem.listWaterItemWeek.observe(this) {
            var sum = 0.0

            for (item in it) {
                sum += item.dayVolume
            }

            // reg for volume
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            df.format(sum)

            binding.textPart1Week.text = (df.format(sum).toDouble()).toString()

            if (binding.textPart1Week.text == binding.textPart3Week.text) {
                binding.progressDrinkWeek.progress = binding.progressDrinkWeek.max
            } else binding.progressDrinkWeek.progress = floor(sum * 100).toInt()

        }




    }

    // calculate water norm
    private fun normalWater(kg: Float): String {

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format((kg * 35.0) / 1000.0)
    }


}