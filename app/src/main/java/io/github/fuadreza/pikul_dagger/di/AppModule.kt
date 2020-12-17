package io.github.fuadreza.pikul_dagger.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.fuadreza.pikul_dagger.data.local.LocalDatabase
import io.github.fuadreza.pikul_dagger.data.local.dao.UserProgressDao
import io.github.fuadreza.pikul_dagger.data.remote.UnivFirestore
import io.github.fuadreza.pikul_dagger.repository.SoalRepository
import io.github.fuadreza.pikul_dagger.repository.UserProgressRepository
import io.github.fuadreza.pikul_dagger.repository.UserRepository

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 30/09/2020.
 *
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideUserRepo() = UserRepository()

    @Provides
    fun provideUnivFirestore() = UnivFirestore()

    @Provides
    fun provideSoalRepository() = SoalRepository()

    @Provides
    fun providesUserProgressDao(@ApplicationContext appContext: Context): UserProgressDao {
        return LocalDatabase.getDatabase(appContext).userProgressDao()
    }

    @Provides
    fun providesUserProgressRepo(userProgressDao: UserProgressDao) =
        UserProgressRepository(userProgressDao)
}