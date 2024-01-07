package ppatsrrif.one.waterstate.domain.repository

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ppatsrrif.one.waterstate.repository.RepositoryImp
import ppatsrrif.one.waterstate.repository.storage.UserStorage

@Module
@InstallIn(SingletonComponent::class)
class ModuleRepository {

    @Provides
    fun provideRepository(
        userStorage: UserStorage,
        @ApplicationContext context: Context
    ): Repository {
        return RepositoryImp(userStorage, context)
    }

}