package ppatsrrif.one.waterstate.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ppatsrrif.one.waterstate.repository.database.table.WaterItemTable
import java.util.*

@Dao
interface DaoManager {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(waterItemTable: WaterItemTable)

    @Delete
    fun deleteItem(waterItemTable: WaterItemTable)

    @Query("SELECT * FROM water_item WHERE date >= :startOfDay AND date < :endOfDay")
    fun getByDate(startOfDay: Date, endOfDay: Date): LiveData<List<WaterItemTable>>

}