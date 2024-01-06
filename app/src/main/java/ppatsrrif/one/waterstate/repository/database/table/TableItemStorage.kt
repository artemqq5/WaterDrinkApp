package ppatsrrif.one.waterstate.repository.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "table_item_storage1")
data class TableItemStorage(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val typeDay: Int,
    val time: String,
    var volumeWater: Double,
)