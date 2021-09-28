package ppatsrrif.one.waterstate.mainPart.roomDataBase

import android.annotation.SuppressLint
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database (
    entities = [TableItemStorage::class],
    version = 1
)
@TypeConverters (ConvertorDataType::class)
abstract class DataBase : RoomDatabase() {

    abstract fun createDao(): DaoManager

}