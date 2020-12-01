package io.github.fuadreza.pikul_dagger.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SoalRepository constructor() {
    private val firestore = FirebaseFirestore.getInstance()

    fun getAllSoal(): CollectionReference {
        val docRef = firestore.collection("soals")
        return docRef
    }

    fun getSoalByCategory(kategori: String): Query {
        val docRef = firestore.collection("soals")
            .whereEqualTo("kategori", kategori)

        return docRef
    }

}