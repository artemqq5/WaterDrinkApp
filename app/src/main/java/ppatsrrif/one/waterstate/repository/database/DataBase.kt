package ppatsrrif.one.waterstate.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ppatsrrif.one.waterstate.repository.database.table.WaterItemTable

@Database(
    entities = [WaterItemTable::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ConvertorDataType::class)
abstract class DataBase : RoomDatabase() {

    abstract fun createDao(): DaoManager

    companion object {
        const val DATABASE_NAME = "data_base-water_state11"
    }

}