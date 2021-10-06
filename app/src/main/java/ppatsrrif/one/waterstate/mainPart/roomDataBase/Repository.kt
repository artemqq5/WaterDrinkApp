package ppatsrrif.one.waterstate.mainPart.roomDataBase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.*

private const val DATABASE_NAME = "data_base-water_state8"

class Repository private constructor(context: Context) {

    private val dataBase: DataBase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            DataBase::class.java,
            DATABASE_NAME
        ).build()

    }
    private val daoManager = dataBase.createDao()


    // get all object from storage
    fun getAllItem(): LiveData<List<TableItemStorage>> = daoManager.getAll()

    // get some object from storage
    fun getSomeDay(type: String): LiveData<List<TableItemStorage>> =
        daoManager.getSomeDay(type)

    // add item to Table
    suspend fun addItem(item: TableItemStorage) = daoManager.insertItem(item)

    // delete item from Table
    suspend fun deleteItem(id: UUID) = daoManager.deleteItem(id)


    //add item goals
    suspend fun addGoals(item: TableIItemStorageGoals) = daoManager.addGoals(item)

    //update goals status
    suspend fun updateGoals(dayOFWeek: Int, status: Int) =
        daoManager.updateGoals(dayOFWeek, status)

    // get all object from Table Goals
    fun getGoals(): LiveData<List<TableIItemStorageGoals>> = daoManager.getGoals()


    //delete dt goals
    suspend fun deleteGoals() = daoManager.deleteGoals()

    //delete dt storage
    suspend fun deleteWeek() = daoManager.deleteWeek()

    companion object {

        private var INSTANCE: Repository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = Repository(context)
            }
        }


        fun getInstance (): Repository {
            return INSTANCE ?:
            throw IllegalStateException("Repository must be initialize")
        }

    }

}