package ppatsrrif.one.waterstate.mainPart.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.ActivityMoreStatsBinding
import ppatsrrif.one.waterstate.mainPart.helperClasses.CompareDates
import ppatsrrif.one.waterstate.mainPart.helperClasses.DateHelper
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelUser
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

class MoreStats : AppCompatActivity() {

    private lateinit var binding: ActivityMoreStatsBinding
    private val viewModelItem: ViewModelItem by lazy {
        ViewModelProvider(this)[ViewModelItem::class.java]
    }
    private val liveDataUser: ViewModelUser by viewModels()

    private val dateHelper by lazy {
        DateHelper()
    }

    private val dateCheck by lazy {
        CompareDates(
            SharedPreferencesHelper(this),
            viewModelItem)
    }

    private val listOfIdImageStatus =
        arrayOf(R.id.Mon, R.id.Tue, R.id.Wed, R.id.Thu, R.id.Fri, R.id.Sat, R.id.Sun)


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

            binding.textPart3.text = normalWater(it.toDouble(), 1.0).toString()
            binding.textPart3Week.text = (normalWater(it.toDouble(), 7.0)).toString()

            binding.progressDrink.max = round(normalWater(it.toDouble(), 100.0)).toInt()
            binding.progressDrinkWeek.max = round(normalWater(it.toDouble(), 700.0)).toInt()

            Log.i("sdfe34f234f", "${binding.progressDrink.max}, ${binding.progressDrinkWeek.max}")
        }

        viewModelItem.date.observe(this) { dateNow ->

            // set progress for Day
            viewModelItem.listSomeDay(dateNow).observe(this) { listItems ->
                var sum = 0.0

                for (item in listItems) {
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
                    binding.progressDrink.progress = round(sum * 100).toInt()

                }

                Log.i("sdfe34f234f", "${round(sum * 100).toInt()}, $sum, ${binding.progressDrink.max}")
                if(round(sum * 100).toInt() >= binding.progressDrink.max) {
                    viewModelItem.updateGoals(getNowDate(), 1)
                }  else viewModelItem.updateGoals(getNowDate(), 0)

            }

        }

        // set progress for Week
        viewModelItem.listWaterItem.observe(this) {
            var sum = 0.0

            for (item in it) {
                sum += item.volumeWater
            }

            // reg for volume
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            df.format(sum)

            binding.textPart1Week.text = (df.format(sum).toDouble()).toString()

            if (binding.textPart1Week.text == binding.textPart3Week.text) {
                binding.progressDrinkWeek.progress = binding.progressDrinkWeek.max
            } else binding.progressDrinkWeek.progress = round(sum * 100).toInt()

        }



        // set goals and count text
        viewModelItem.listGoals.observe(this) {
            var sumCompleted = 0

            for(n in it){

                if(n.status == 1) {
                    findViewById<ImageView>(listOfIdImageStatus[n.dayOFWeek-1])
                        .setImageResource(R.drawable.ic_goal_completed)

                    sumCompleted++

                } else findViewById<ImageView>(listOfIdImageStatus[n.dayOFWeek-1])
                    .setImageResource(R.drawable.ic_goal)
            }

            binding.textCompleted.text = "$sumCompleted/7"
        }


    }

    // calculate water norm
    private fun normalWater(kg: Double, num: Double): Double {

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(((kg * 35.0) / 1000.0) * num).toDouble()
    }

    private fun getNowDate(): Int{

        var dateFormat = SimpleDateFormat("EEEE")

        return when(dateFormat.format(Date())) {
            "Monday" -> 1
            "Tuesday" -> 2
            "Wednesday" -> 3
            "Thursday" -> 4
            "Friday" -> 5
            "Saturday" -> 6
            "Sunday" -> 7

            else -> 0
        }
    }


    override fun onResume() {
        super.onResume()

        dateCheck.checkWeek(dateHelper.getWeek())
        viewModelItem.date.value = dateHelper.getDay()
    }

}