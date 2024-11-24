package ppatsrrif.one.waterstate.domain.usecase

import android.annotation.SuppressLint
import ppatsrrif.one.waterstate.domain.repository.model.UserGender
import ppatsrrif.one.waterstate.domain.repository.model.UserModel
import javax.inject.Inject
import kotlin.math.roundToInt

class VolumeUseCase @Inject constructor() {

    fun millilitersToLiters(volume: Double): Double {
        return (volume / 1000.0).let { Math.round(it * 100) / 100.0 }
    }

    fun waterAlgorithm(user: UserModel): Double {
        val genderCof = if (user.gender == UserGender.Male) 1.0 else 0.9

        return (user.weight * 30) * user.physical * genderCof
    }

    fun waterToProgressFormat(volume: Double): Int {
        return (volume * 10).toInt()
    }
}