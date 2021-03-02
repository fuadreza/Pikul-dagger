package io.github.fuadreza.pikul_dagger.repository

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import io.github.fuadreza.pikul_dagger.data.local.dao.UserProgressDao
import io.github.fuadreza.pikul_dagger.data.local.entity.UserProgress
import javax.inject.Inject

class UserProgressRepository @Inject constructor(private val auth: FirebaseAuth, private val userProgressDao: UserProgressDao){

    var user: FirebaseUser? = auth.currentUser

    private val firestore = FirebaseFirestore.getInstance()

    val userProgress: LiveData<UserProgress> = userProgressDao.getUserProgress()

    fun fetchUserProgress() = userProgressDao.getUserProgress()

    fun getUserProgress(uid: String): DocumentReference {
        return firestore.collection("user_score").document(uid)
    }

    fun getUserProgress(): DocumentReference {
        return firestore.collection("user_score").document(user?.uid.toString())
    }
}