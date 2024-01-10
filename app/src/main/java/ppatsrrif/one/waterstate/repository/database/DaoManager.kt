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

//    @Query("SELECT * FROM table_item_storage1 WHERE typeDay=(:type)")
//    fun getSomeDay(type: Int): LiveData<List<WaterItemTable>>


    //for goals
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addGoals(item: TableIItemStorageGoals)

//    @Query("UPDATE table_item_storage_goals1 SET status=(:status) WHERE dayOFWeek=(:dayOFWeek)")
//    suspend fun updateGoals(dayOFWeek: Int, status: Int)
//
//    @Query("SELECT * FROM table_item_storage_goals1")
//    fun getGoals(): LiveData<List<TableIItemStorageGoals>>


    // delete dt goals
//    @Query("DELETE FROM table_item_storage_goals1")
//    suspend fun deleteGoals()

    // delete dt week
//    @Query("DELETE FROM table_item_storage1")
//    suspend fun deleteWeek()

}