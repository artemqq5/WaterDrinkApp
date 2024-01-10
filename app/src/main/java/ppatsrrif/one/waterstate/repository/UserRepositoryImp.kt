package ppatsrrif.one.waterstate.repository

import ppatsrrif.one.waterstate.domain.repository.UserRepository
import ppatsrrif.one.waterstate.domain.repository.model.LoadMode
import ppatsrrif.one.waterstate.domain.repository.model.UserGender
import ppatsrrif.one.waterstate.domain.repository.model.UserModel
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

//    private val dataBase: DataBase by lazy {
//        Room.databaseBuilder(
//            context.applicationContext,
//            DataBase::class.java,
//            DATABASE_NAME
//        ).build()
//
//    }
//
//    private val daoManager by lazy {
//        dataBase.createDao()
//    }

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

    private fun String.toLoadMode(): LoadMode {
        return LoadMode.valueOf(this)
    }

    private fun Int.toUserGender(): UserGender {
        return if (this == 1) UserGender.Male else UserGender.Female
    }

    private fun UserGender.toInt(): Int {
        return if (this == UserGender.Male) 1 else -1
    }


//    // get all object from storage
//    fun getAllItem(): LiveData<List<TableItemStorage>> = daoManager.getAll()
//
//    // get some object from storage
//    fun getSomeDay(type: Int): LiveData<List<TableItemStorage>> =
//        daoManager.getSomeDay(type)
//
//    // add item to Table
//    suspend fun addItem(item: TableItemStorage) = daoManager.insertItem(item)
//
//    // delete item from Table
//    suspend fun deleteItem(id: UUID) = daoManager.deleteItem(id)
//
//
//    //add item goals
//    suspend fun addGoals(item: TableIItemStorageGoals) = daoManager.addGoals(item)
//
//    //update goals status
//    suspend fun updateGoals(dayOFWeek: Int, status: Int) =
//        daoManager.updateGoals(dayOFWeek, status)
//
//    // get all object from Table Goals
//    fun getGoals(): LiveData<List<TableIItemStorageGoals>> = daoManager.getGoals()
//
//
//    //delete dt goals
//    suspend fun deleteGoals() = daoManager.deleteGoals()
//
//    //delete dt storage
//    suspend fun deleteWeek() = daoManager.deleteWeek()

}