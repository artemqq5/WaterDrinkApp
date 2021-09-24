package ppatsrrif.one.waterstate.mainPart.roomDataBase

import androidx.room.TypeConverter
import java.util.*

class ConvertorDataType {

    @TypeConverter
    fun fromUUID (uuid: UUID): String {
        return uuid.toString()
    }

    @TypeConverter
    fun toUUID (str: String): UUID {
        return UUID.fromString(str)
    }

    @TypeConverter
    fun fromDouble (volume: Double): String {
        return volume.toString()
    }

    @TypeConverter
    fun toDouble (str: String): Double {
        return str.toDouble()
    }

}