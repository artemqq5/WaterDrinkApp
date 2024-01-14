package ppatsrrif.one.waterstate.domain.repository

import androidx.lifecycle.LiveData
import ppatsrrif.one.waterstate.domain.repository.model.WaterModel
import java.util.Date

interface WaterRepository {

    fun addWaterItem(waterItem: WaterModel)

    fun deleteWaterItem(waterItem: WaterModel)

    fun getWaterItemsByDate(startOfDay: Date, endOfDay: Date): LiveData<List<WaterModel>>

}