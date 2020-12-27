package io.github.fuadreza.pikul_dagger.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import io.github.fuadreza.pikul_dagger.model.JawabanUser

class SoalRepository constructor() {
    private val firestore = FirebaseFirestore.getInstance()

    fun getAllSoal(): CollectionReference {
        val docRef = firestore.collection("soals")
        return docRef
    }

    fun getSoalById(soalId: String): DocumentReference {
        val docRef = firestore.collection("soals").document(soalId)

        return docRef
    }

    fun getUserScore(userId: String) {

    }

    fun saveUserScore(jawabanUser: JawabanUser) : Task<Void>{
        return firestore.collection("user_score").document(jawabanUser.uid).set(jawabanUser)
    }

}