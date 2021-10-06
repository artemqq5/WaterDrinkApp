package ppatsrrif.one.waterstate.mainPart.roomDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database (
    entities = [TableItemStorage::class, TableIItemStorageGoals::class],
    version = 1
)
@TypeConverters (ConvertorDataType::class)
abstract class DataBase : RoomDatabase() {

    abstract fun createDao(): DaoManager

}