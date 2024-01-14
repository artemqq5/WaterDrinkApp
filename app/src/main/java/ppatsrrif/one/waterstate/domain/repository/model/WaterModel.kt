package ppatsrrif.one.waterstate.domain.repository.model

import java.util.Date

data class WaterModel(
    val id: Int? = null,
    val date: Date,
    val volumeWater: Double
)