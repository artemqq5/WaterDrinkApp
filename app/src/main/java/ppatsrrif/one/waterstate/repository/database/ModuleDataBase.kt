package ppatsrrif.one.waterstate.repository.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ppatsrrif.one.waterstate.repository.database.DataBase.Companion.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleDataBase {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DataBase {
        return Room.databaseBuilder(
            appContext,
            DataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDaoManager(dataBase: DataBase): DaoManager {
        return dataBase.createDao()
    }

}