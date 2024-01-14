package ppatsrrif.one.waterstate.domain.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ppatsrrif.one.waterstate.repository.UserRepositoryImp
import ppatsrrif.one.waterstate.repository.WaterRepositoryImp
import ppatsrrif.one.waterstate.repository.database.DaoManager
import ppatsrrif.one.waterstate.repository.storage.UserStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleRepository {

    @Provides
    @Singleton
    fun provideUserRepository(userStorage: UserStorage): UserRepository {
        return UserRepositoryImp(userStorage)
    }

    @Provides
    @Singleton
    fun provideWaterRepository(
        daoManager: DaoManager
    ): WaterRepository {
        return WaterRepositoryImp(daoManager)
    }

}