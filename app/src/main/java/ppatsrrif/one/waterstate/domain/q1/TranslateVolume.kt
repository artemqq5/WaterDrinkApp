package ppatsrrif.one.waterstate.domain.q1

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

    fun millilitersToLiters(volume: Double): Double {
       return volume / 1000.0
    }
}