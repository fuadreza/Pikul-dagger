package io.github.fuadreza.pikul_dagger.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
import javax.inject.Singleton

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 30/09/2020.
 *
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideUserRepo(auth: FirebaseAuth, db: FirebaseFirestore) = UserRepository(auth, db)

    @Provides
    fun provideUnivFirestore() = UnivFirestore()

    @Provides
    fun provideSoalRepository(auth: FirebaseAuth) = SoalRepository(auth)

    @Provides
    fun providesUserProgressDao(@ApplicationContext appContext: Context): UserProgressDao {
        return LocalDatabase.getDatabase(appContext).userProgressDao()
    }

    @Provides
    fun providesUserProgressRepo(auth: FirebaseAuth, userProgressDao: UserProgressDao) =
        UserProgressRepository(auth, userProgressDao)

    @Provides
    @Singleton
    fun provideAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideDb() = FirebaseFirestore.getInstance()
}