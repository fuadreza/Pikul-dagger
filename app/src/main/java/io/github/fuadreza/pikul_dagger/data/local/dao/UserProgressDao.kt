package io.github.fuadreza.pikul_dagger.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.fuadreza.pikul_dagger.data.local.LocalDatabase
import io.github.fuadreza.pikul_dagger.data.local.entity.UserProgress

@Dao
interface UserProgressDao {

    @Query("SELECT * FROM ${LocalDatabase.TB_USER_PROGRESS}")
    fun getUserProgress(): LiveData<UserProgress>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userProgress: UserProgress)
}