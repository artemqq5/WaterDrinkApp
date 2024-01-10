package ppatsrrif.one.waterstate.repository.storage

interface UserStorage {

    fun getStatusRecommendation(): Boolean
    fun setStatusRecommendation(status: Boolean)

    fun setLoadModePref(loadMode: String)
    fun getLoadModePref(): String?

    fun setUserName(name: String)
    fun getUserName(): String

    fun setUserWeight(weight: Float)
    fun getUserWeight(): Float

    fun setUserGender(gender: Int)
    fun getUserGender(): Int

    fun setUserPhysical(physical: Float)
    fun getUserPhysical(): Float

}