package ppatsrrif.one.waterstate.repository.database

import androidx.room.TypeConverter
import java.util.*

class ConvertorDataType {

    @TypeConverter
    fun fromDate(value: Date?): Long? {
        return value?.time
    }

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

}