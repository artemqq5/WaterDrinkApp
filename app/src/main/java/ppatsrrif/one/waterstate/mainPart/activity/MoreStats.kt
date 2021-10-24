package ppatsrrif.one.waterstate.mainPart.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.ActivityMoreStatsBinding
import ppatsrrif.one.waterstate.mainPart.helperClasses.CompareDates
import ppatsrrif.one.waterstate.mainPart.helperClasses.DateHelper
import ppatsrrif.one.waterstate.mainPart.helperClasses.TranslateVolume
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelUser
import java.text.SimpleDateFormat
import java.util.*

class MoreStats : AppCompatActivity() {

    private val binding by lazy {
        ActivityMoreStatsBinding.inflate(layoutInflater)
    }
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
            viewModelItem
        )
    }

    private val translateVolume by lazy {
        TranslateVolume()
    }

    private val listOfIdImageStatus =
        arrayOf(R.id.Mon, R.id.Tue, R.id.Wed, R.id.Thu, R.id.Fri, R.id.Sat, R.id.Sun)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }

        // set max progress
        liveDataUser.liveDataWeight.observe(this) {

            binding.textPart3.text = translateVolume.normalWaterUI(it, 1).toString()
            binding.textPart3Week.text = translateVolume.normalWaterUI(it, 2).toString()

            binding.progressDrink.max = translateVolume.normalWaterProgress(it, 1)
            binding.progressDrinkWeek.max = translateVolume.normalWaterProgress(it, 2)

        }

        viewModelItem.date.observe(this) { dateNow ->

            // set progress for Day
            viewModelItem.listSomeDay(dateNow).observe(this) { listItems ->
                var sum = 0.0

                for (item in listItems) {
                    sum += item.volumeWater

                }


                binding.textPart1.text = translateVolume.addWater(sum, 1).toString()

                if (binding.textPart1.text == binding.textPart3.text) {
                    binding.progressDrink.progress = binding.progressDrink.max

                } else {
                    binding.progressDrink.progress = translateVolume.addWater(sum, 2).toInt()

                }

                if (translateVolume.addWater(sum, 2).toInt() >= binding.progressDrink.max) {
                    viewModelItem.updateGoals(getNowDate(), 1)
                } else viewModelItem.updateGoals(getNowDate(), 0)


            }

        }

        // set progress for Week
        viewModelItem.listWaterItem.observe(this) {
            var sum = 0.0

            for (item in it) {
                sum += item.volumeWater
            }

            binding.textPart1Week.text = translateVolume.addWater(sum, 1).toString()

            if (binding.textPart1Week.text == binding.textPart3Week.text) {
                binding.progressDrinkWeek.progress = binding.progressDrinkWeek.max
            } else binding.progressDrinkWeek.progress = translateVolume.addWater(sum, 2).toInt()


        }


        // set goals and count text
        viewModelItem.listGoals.observe(this) {
            var sumCompleted = 0

            for (n in it) {

                if (n.status == 1) {
                    findViewById<ImageView>(listOfIdImageStatus[n.dayOFWeek - 1])
                        .setImageResource(R.drawable.ic_goal_completed)

                    sumCompleted++

                } else findViewById<ImageView>(listOfIdImageStatus[n.dayOFWeek - 1])
                    .setImageResource(R.drawable.ic_goal)
            }

            binding.textCompleted.text = "$sumCompleted/7"
        }


    }


    private fun getNowDate(): Int {

        val dateFormat = SimpleDateFormat("u")

        return dateFormat.format(Date()).toInt()
    }


    override fun onResume() {
        super.onResume()

        dateCheck.checkWeek(dateHelper.getWeek())
        viewModelItem.date.value = dateHelper.getDay()

        setWeekDate()

    }

    private fun setWeekDate() {
        // object calendar
        var calendar = Calendar.getInstance(Locale.getDefault())
        // set monday date
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        // write to string var date
        var sDateW = calendar.get(Calendar.DAY_OF_MONTH).toString() + " - "
        // set saturday date
        calendar.add(Calendar.DAY_OF_WEEK, 6)
        // write to string var date too
        sDateW += calendar.get(Calendar.DAY_OF_MONTH).toString()
        //set Date for week
        binding.textDateWeek.text = "${SimpleDateFormat("MMMM").format(Date())} $sDateW"
    }

}