package ppatsrrif.one.waterstate.repository.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "water_item")
data class WaterItemTable(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val date: Date,
    var volumeWater: Double,
)