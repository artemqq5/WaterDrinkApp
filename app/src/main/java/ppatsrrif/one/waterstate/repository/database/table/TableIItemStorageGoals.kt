package ppatsrrif.one.waterstate.repository.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_item_storage_goals1")
data class TableIItemStorageGoals(
    @PrimaryKey val dayOFWeek: Int,
    var status: Int
)