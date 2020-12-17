package io.github.fuadreza.pikul_dagger.repository

import androidx.lifecycle.LiveData
import io.github.fuadreza.pikul_dagger.data.local.dao.UserProgressDao
import io.github.fuadreza.pikul_dagger.data.local.entity.UserProgress
import javax.inject.Inject

class UserProgressRepository @Inject constructor(private val userProgressDao: UserProgressDao){

    val userProgress: LiveData<UserProgress> = userProgressDao.getUserProgress()

    fun fetchUserProgress() = userProgressDao.getUserProgress()
}