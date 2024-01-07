package ppatsrrif.one.waterstate.domain.repository

import ppatsrrif.one.waterstate.domain.repository.model.LoadMode
import ppatsrrif.one.waterstate.domain.repository.model.UserModel


interface UserRepository {

    fun getLoadMode(): LoadMode
    fun setLoadMode(loadMode: LoadMode)

    fun getUser(): UserModel
    fun setUser(userModel: UserModel)


}