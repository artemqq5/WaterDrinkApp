package ppatsrrif.one.waterstate.mainPart.roomDataBase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.*

private const val DATABASE_NAME = "data_base-water_state6"

class Repository private constructor(context: Context) {

    private val dataBase: DataBase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            DataBase::class.java,
            DATABASE_NAME
        ).build()

    }
    private val daoManager = dataBase.createDao()


    // get all object from Table Day
    fun getAllItem(): LiveData<List<TableItemStorage>> = daoManager.getAll()

    // get some object from Table Week
    fun getItemFromWeek(): LiveData<List<TableItemStorageWeek>> =
        daoManager.getAllFromWeek()

    // add item to Table Day and Week
    suspend fun addItem(item: TableItemStorage) {
        dataBase.createDao().insertItem(item)
        dataBase.createDao().insertItemToWeek(
            TableItemStorageWeek(
                item.id, item.volumeWater
            )
        )
    }

    // delete item from Table Day and Week
    suspend fun deleteItem(id: UUID) {
        dataBase.createDao().deleteItem(id)
        dataBase.createDao().deleteItemFromWeek(id)
    }


    //add item goals
    suspend fun addGoals(item: TableIItemStorageGoals) =
        daoManager.addGoals(item)

    //update goals status
    suspend fun updateGoals(dayOFWeek: Int, status: Int) =
        daoManager.updateGoals(dayOFWeek, status)

    // get all object from Table Goals
    fun getGoals(): LiveData<List<TableIItemStorageGoals>> = daoManager.getGoals()

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