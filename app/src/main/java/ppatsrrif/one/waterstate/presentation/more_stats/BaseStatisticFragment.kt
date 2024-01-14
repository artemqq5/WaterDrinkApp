package ppatsrrif.one.waterstate.presentation.more_stats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.FragmentBaseStatisticBinding
import ppatsrrif.one.waterstate.domain.usecase.DateUseCase
import ppatsrrif.one.waterstate.domain.usecase.VolumeUseCase
import ppatsrrif.one.waterstate.presentation.home.activity.MainActivity
import ppatsrrif.one.waterstate.presentation.viewmodel.UserViewModel
import ppatsrrif.one.waterstate.presentation.viewmodel.WaterViewModel
import javax.inject.Inject

@AndroidEntryPoint
class BaseStatisticFragment : Fragment() {

    private lateinit var binding: FragmentBaseStatisticBinding

    private val waterViewModel: WaterViewModel by activityViewModels()

    private val userViewModel: UserViewModel by activityViewModels()

    @Inject
    lateinit var volumeUseCase: VolumeUseCase

    @Inject
    lateinit var dateUseCase: DateUseCase

    private val waterGoalsList by lazy {
        listOf(
            binding.day0before,
            binding.day1before,
            binding.day2before,
            binding.day3before,
            binding.day4before,
            binding.day5before,
            binding.day6before
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBaseStatisticBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set back button
        binding.topAppBar.setNavigationOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }

        waterViewModel.listCurrentWater.observe(viewLifecycleOwner) { listWater ->
            // set current day water sum and user water norm
            val sumCurrentWater = listWater.sumOf { it.volumeWater }
            val userWaterNorm = volumeUseCase.waterAlgorithm(userViewModel.getDefaultUser())

            // set water daily norm with user norm
            binding.dailyNorm.text = resources.getString(
                R.string.water_of_norm_value,
                volumeUseCase.millilitersToLiters(sumCurrentWater).toString(),
                volumeUseCase.millilitersToLiters(userWaterNorm).toString()
            )

            // set progress with data water daily norm with user
            binding.dailyProgress.max = volumeUseCase.waterToProgressFormat(
                volumeUseCase.millilitersToLiters(userWaterNorm)
            )

            binding.dailyProgress.progress = volumeUseCase.waterToProgressFormat(
                volumeUseCase.millilitersToLiters(sumCurrentWater)
            )

        }

        waterViewModel.listCurrentWeekWater.observe(viewLifecycleOwner) { listWeekWater ->
            // set current week water sum and user water weekly norm
            val sumCurrentWeekWater = listWeekWater.sumOf { it.volumeWater }
            val userWaterNorm = volumeUseCase.waterAlgorithm(userViewModel.getDefaultUser()) * 7

            // set water weekly norm with user norm
            binding.weeklyNorm.text = resources.getString(
                R.string.water_of_norm_value,
                volumeUseCase.millilitersToLiters(sumCurrentWeekWater).toString(),
                volumeUseCase.millilitersToLiters(userWaterNorm).toString()
            )

            // set progress with data water weekly norm with user
            binding.weeklyProgress.max = volumeUseCase.waterToProgressFormat(
                volumeUseCase.millilitersToLiters(userWaterNorm)
            )

            binding.weeklyProgress.progress = volumeUseCase.waterToProgressFormat(
                volumeUseCase.millilitersToLiters(sumCurrentWeekWater)
            )

            // get current week date (start - end)
            val startWeek = dateUseCase.toWeeklyDateFormat(dateUseCase.getCurrentWeekStartDate())
            val endWeek = dateUseCase.toWeeklyDateFormat(dateUseCase.getCurrentWeekEndDate())

            // set current week date to week norm
            binding.currentWeekDate.text =
                resources.getString(R.string.current_week_value, startWeek, endWeek)
        }

        waterViewModel.listGoalsWater.observe(viewLifecycleOwner) { listGoals ->

            val userWaterNorm = volumeUseCase.millilitersToLiters(
                volumeUseCase.waterAlgorithm(userViewModel.getDefaultUser())
            )

            waterGoalsList.forEachIndexed { index, imageView ->
                val waterIntakeForDay = volumeUseCase.millilitersToLiters(listGoals.filter {
                    dateUseCase.isDateInRange(
                        it.date,
                        dateUseCase.getCurrentStartDate(index),
                        dateUseCase.getCurrentEndDate(index)
                    )
                }.sumOf { it.volumeWater })

                imageView.setImageResource(
                    if (waterIntakeForDay >= userWaterNorm) R.drawable.ic_goal_completed
                    else R.drawable.ic_goal
                )
            }

        }


    }

}