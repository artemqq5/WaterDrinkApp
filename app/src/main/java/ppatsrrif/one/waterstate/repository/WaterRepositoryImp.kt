package ppatsrrif.one.waterstate.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ppatsrrif.one.waterstate.domain.repository.WaterRepository
import ppatsrrif.one.waterstate.domain.repository.model.WaterModel
import ppatsrrif.one.waterstate.repository.database.DaoManager
import ppatsrrif.one.waterstate.repository.database.table.WaterItemTable
import java.util.Date
import javax.inject.Inject

class WaterRepositoryImp @Inject constructor(
    private val daoManager: DaoManager,
) : WaterRepository {
    override fun addWaterItem(waterItem: WaterModel) {
        daoManager.insertItem(waterItem.toWaterItemTable())
    }

    override fun deleteWaterItem(waterItem: WaterModel) {
        daoManager.deleteItem(waterItem.toWaterItemTable())
    }

    override fun getWaterItemByDate(startOfDay: Date, endOfDay: Date): LiveData<List<WaterModel>> {
        return daoManager.getByDate(startOfDay, endOfDay).map { list ->
            list.map { waterItemTable -> waterItemTable.toWaterModel() }
        }
    }

    private fun WaterItemTable.toWaterModel(): WaterModel {
        return WaterModel(
            id = this.id,
            date = this.date,
            volumeWater = this.volumeWater
        )
    }

    private fun WaterModel.toWaterItemTable(): WaterItemTable {
        return WaterItemTable(
            id = this.id,
            date = this.date,
            volumeWater = this.volumeWater
        )
    }


}