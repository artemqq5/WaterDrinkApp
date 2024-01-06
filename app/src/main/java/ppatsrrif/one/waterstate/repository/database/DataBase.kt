package ppatsrrif.one.waterstate.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ppatsrrif.one.waterstate.repository.database.table.TableIItemStorageGoals
import ppatsrrif.one.waterstate.repository.database.table.TableItemStorage

@Database(
    entities = [TableItemStorage::class, TableIItemStorageGoals::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ConvertorDataType::class)
abstract class DataBase : RoomDatabase() {

    abstract fun createDao(): DaoManager

}