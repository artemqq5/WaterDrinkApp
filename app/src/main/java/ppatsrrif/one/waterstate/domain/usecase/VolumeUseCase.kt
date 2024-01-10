package ppatsrrif.one.waterstate.domain.usecase

import kotlin.math.roundToInt

class VolumeUseCase {

    fun millilitersToLiters(volume: Double): Double {
        return (volume / 1000.0 * 10).roundToInt() / 10.0
    }
}