package ppatsrrif.one.waterstate.mainPart.roomDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_item_storage")
data class TableItemStorage(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val typeDay: String,
    val time: String,
    var volumeWater: Double,
)

@Entity(tableName = "table_item_storage_goals")
data class TableIItemStorageGoals(
    @PrimaryKey val dayOFWeek: Int,
    var status: Int
)