package ppatsrrif.one.waterstate.domain.repository.model

data class UserModel(
    val name: String,
    val weight: Float,
    val gender: UserGender,
    val physical: Float
)