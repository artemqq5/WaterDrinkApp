package ppatsrrif.one.waterstate.repository

import androidx.lifecycle.LiveData
import ppatsrrif.one.waterstate.ApplicationStart.Companion.log
import ppatsrrif.one.waterstate.domain.repository.WaterRepository
import ppatsrrif.one.waterstate.repository.database.DaoManager
import ppatsrrif.one.waterstate.domain.repository.database_table.WaterItemTable
import java.util.Date
import javax.inject.Inject

class WaterRepositoryImp @Inject constructor(
    private val daoManager: DaoManager,
) : WaterRepository {
    override fun addWaterItem(waterItem: WaterItemTable) {
        daoManager.insertItem(waterItem)
    }

    override fun deleteWaterItem(waterItem: WaterItemTable) {
        daoManager.deleteItem(waterItem)
    }

    override fun getWaterItemByDate(startOfDay: Date, endOfDay: Date): LiveData<List<WaterItemTable>> {
        return daoManager.getByDate(startOfDay, endOfDay)
    }


}