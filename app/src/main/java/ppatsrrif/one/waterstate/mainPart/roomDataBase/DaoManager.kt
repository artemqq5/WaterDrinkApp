package ppatsrrif.one.waterstate.mainPart.roomDataBase

import android.os.storage.StorageVolume
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface DaoManager {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(tableItemStorage: TableItemStorage)

    @Query("DELETE FROM table_item_storage WHERE id=(:id)")
    suspend fun deleteItem(id: UUID)

    @Query("SELECT * FROM table_item_storage")
    fun getAll(): LiveData<List<TableItemStorage>>

}