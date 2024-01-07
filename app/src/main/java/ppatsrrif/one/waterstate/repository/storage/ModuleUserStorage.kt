package ppatsrrif.one.waterstate.repository.storage

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ModuleUserStorage {

    @Provides
    fun provideUserStorage(
        @ApplicationContext context: Context
    ): UserStorage {
        return UserUserStoragePreference(context)
    }

}