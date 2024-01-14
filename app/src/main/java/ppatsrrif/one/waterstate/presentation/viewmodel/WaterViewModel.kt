package ppatsrrif.one.waterstate.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ppatsrrif.one.waterstate.ApplicationStart.Companion.log
import ppatsrrif.one.waterstate.domain.repository.WaterRepository
import ppatsrrif.one.waterstate.domain.repository.model.WaterModel
import ppatsrrif.one.waterstate.domain.usecase.DateUseCase
import javax.inject.Inject

@HiltViewModel
class WaterViewModel @Inject constructor(
    private val waterRepository: WaterRepository,
    private val dateUseCase: DateUseCase
) : ViewModel() {

    private val excCoroutine = CoroutineExceptionHandler { _, throwable ->
        log("WaterViewModel $throwable")
    }

    val listCurrentWater: LiveData<List<WaterModel>> = waterRepository.getWaterItemsByDate(
        dateUseCase.getCurrentStartDate(),
        dateUseCase.getCurrentEndDate()
    )

    val listCurrentWeekWater: LiveData<List<WaterModel>> = waterRepository.getWaterItemsByDate(
        dateUseCase.getCurrentWeekStartDate(),
        dateUseCase.getCurrentWeekEndDate()
    )

    val listGoalsWater: LiveData<List<WaterModel>> = waterRepository.getWaterItemsByDate(
        dateUseCase.getCurrentStartDate(6),
        dateUseCase.getCurrentEndDate()
    )

    fun deleteWater(waterModel: WaterModel) {
        viewModelScope.launch(Dispatchers.IO + excCoroutine) {
            waterRepository.deleteWaterItem(waterModel)
        }
    }

    fun addWater(waterModel: WaterModel) {
        viewModelScope.launch(Dispatchers.IO + excCoroutine) {
            waterRepository.addWaterItem(waterModel)
        }
    }

}