package ppatsrrif.one.waterstate.mainPart.roomDataBase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.*

private const val DATABASE_NAME = "data_base-water_state2"

class Repository private constructor(context: Context) {

    private val dataBase: DataBase = Room.databaseBuilder(
        context.applicationContext,
        DataBase::class.java,
        DATABASE_NAME
    ).build()

    private val daoManager = dataBase.createDao()

    fun getAllItem(): LiveData<List<TableItemStorage>> = daoManager.getAll()

    suspend fun addItem(item: TableItemStorage) {
        dataBase.createDao().insertItem(item)
    }

    suspend fun deleteItem(id: UUID) {
        dataBase.createDao().deleteItem(id)
    }

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