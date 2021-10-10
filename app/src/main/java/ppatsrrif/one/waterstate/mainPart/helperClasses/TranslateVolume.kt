package ppatsrrif.one.waterstate.mainPart.helperClasses

import kotlin.math.round

class TranslateVolume {

    // calculate water norm for UI
    fun normalWaterUI(kg: Float, type: Int): Double {

        kg.toDouble()

        return if (type == 1) {
            round(((kg * 35.0) / 1000.0) * 10) / 10.0
        } else round(((kg * 35.0 * 7) / 1000.0) * 10) / 10.0
    }

    // calculate water norm for Progress
    fun normalWaterProgress(kg: Float, type: Int): Int {

        kg.toDouble()

        return if (type == 1) {
            round(((kg * 35.0) / 100)).toInt()
        } else round(((kg * 35.0 * 7) / 100)).toInt()
    }

    // add volume to UI
    fun addWater(volume: Double, type: Int): Double {

        return if (type == 1) {
            round((volume / 1000.0) * 10) / 10.0
        } else round(volume / 100.0)

    }
}