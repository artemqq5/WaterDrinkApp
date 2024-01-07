package ppatsrrif.one.waterstate.domain.repository

import androidx.lifecycle.LiveData
import ppatsrrif.one.waterstate.domain.repository.database_table.WaterItemTable
import java.util.Date

interface WaterRepository {

    fun addWaterItem(waterItem: WaterItemTable)

    fun deleteWaterItem(waterItem: WaterItemTable)

    fun getWaterItemByDate(startOfDay: Date, endOfDay: Date): LiveData<List<WaterItemTable>>

}