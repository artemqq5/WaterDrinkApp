package ppatsrrif.one.waterstate.mainPart.roomDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "table_item_storage")
data class TableItemStorage(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val time: String,
    val volumeWater: Double
)