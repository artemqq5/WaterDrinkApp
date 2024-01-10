package ppatsrrif.one.waterstate.domain.repository

import androidx.lifecycle.LiveData
import ppatsrrif.one.waterstate.domain.repository.model.WaterModel
import ppatsrrif.one.waterstate.repository.database.table.WaterItemTable
import java.util.Date

interface WaterRepository {

    fun addWaterItem(waterItem: WaterModel)

    fun deleteWaterItem(waterItem: WaterModel)

    fun getWaterItemByDate(startOfDay: Date, endOfDay: Date): LiveData<List<WaterModel>>

}