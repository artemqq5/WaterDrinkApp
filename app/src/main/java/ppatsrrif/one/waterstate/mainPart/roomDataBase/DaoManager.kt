package ppatsrrif.one.waterstate.mainPart.roomDataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface DaoManager {


    // Day table
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(tableItemStorage: TableItemStorage)

    //Week table
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItemToWeek(tableItemStorageWeek: TableItemStorageWeek)

    //Delete one from Day Table
    @Query("DELETE FROM table_item_storage WHERE id=(:id)")
    suspend fun deleteItem(id: UUID)

    //Delete one from Week Table
    @Query("DELETE FROM table_item_storage_week WHERE id=(:id)")
    suspend fun deleteItemFromWeek(id: UUID)

    @Query("SELECT * FROM table_item_storage")
    fun getAll(): LiveData<List<TableItemStorage>>

    // for week
    @Query("SELECT * FROM table_item_storage_week")
    fun getAllFromWeek(): LiveData<List<TableItemStorageWeek>>





}