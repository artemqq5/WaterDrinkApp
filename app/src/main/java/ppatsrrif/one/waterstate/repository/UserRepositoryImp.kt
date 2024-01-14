package ppatsrrif.one.waterstate.repository

import android.database.sqlite.SQLiteDatabase
import ppatsrrif.one.waterstate.domain.repository.UserRepository
import ppatsrrif.one.waterstate.domain.repository.model.LoadMode
import ppatsrrif.one.waterstate.domain.repository.model.UserGender
import ppatsrrif.one.waterstate.domain.repository.model.UserModel
import ppatsrrif.one.waterstate.repository.database.DataBase
import ppatsrrif.one.waterstate.repository.storage.UserStorage
import javax.inject.Inject




class UserRepositoryImp @Inject constructor(
    private val userStorage: UserStorage
) : UserRepository {
    override fun getStatusRecommendation(): Boolean {
        return userStorage.getStatusRecommendation()
    }

    override fun setStatusRecommendation(status: Boolean) {
        userStorage.setStatusRecommendation(status)
    }

    override fun getLoadMode(): LoadMode {
        return userStorage.getLoadModePref()?.toLoadMode() ?: LoadMode.Login
    }

    override fun setLoadMode(loadMode: LoadMode) {
        userStorage.setLoadModePref(loadMode.name)
    }

    override fun getUser(): UserModel {
        return UserModel(
            name = userStorage.getUserName(),
            weight = userStorage.getUserWeight(),
            gender = userStorage.getUserGender().toUserGender(),
            physical = userStorage.getUserPhysical()
        )
    }

    override fun setUser(userModel: UserModel) {
        userStorage.setUserName(userModel.name)
        userStorage.setUserWeight(userModel.weight)
        userStorage.setUserGender(userModel.gender.toInt())
        userStorage.setUserPhysical(userModel.physical)
    }

    override fun clearDataUser() {
        userStorage.clearDataUser()
    }

    private fun String.toLoadMode(): LoadMode {
        return LoadMode.valueOf(this)
    }

    private fun Int.toUserGender(): UserGender {
        return if (this == 1) UserGender.Male else UserGender.Female
    }

    private fun UserGender.toInt(): Int {
        return if (this == UserGender.Male) 1 else -1
    }

}