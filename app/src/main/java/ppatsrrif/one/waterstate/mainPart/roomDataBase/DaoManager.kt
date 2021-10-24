package ppatsrrif.one.waterstate.mainPart.roomDataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface DaoManager {

    //for storage water
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(tableItemStorage: TableItemStorage)

    @Query("DELETE FROM table_item_storage1 WHERE id=(:id)")
    suspend fun deleteItem(id: UUID)

    @Query("SELECT * FROM table_item_storage1")
    fun getAll(): LiveData<List<TableItemStorage>>

    @Query("SELECT * FROM table_item_storage1 WHERE typeDay=(:type)")
    fun getSomeDay(type: Int): LiveData<List<TableItemStorage>>


    //for goals
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGoals(item: TableIItemStorageGoals)

    @Query("UPDATE table_item_storage_goals1 SET status=(:status) WHERE dayOFWeek=(:dayOFWeek)")
    suspend fun updateGoals(dayOFWeek: Int, status: Int)

    @Query("SELECT * FROM table_item_storage_goals1")
    fun getGoals(): LiveData<List<TableIItemStorageGoals>>


    // delete dt goals
    @Query("DELETE FROM table_item_storage_goals1")
    suspend fun deleteGoals()

    // delete dt week
    @Query("DELETE FROM table_item_storage1")
    suspend fun deleteWeek()

}