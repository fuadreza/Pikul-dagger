package io.github.fuadreza.pikul_dagger.repository.api

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */

@Singleton
class FirestoreService @Inject constructor() {
    val database = FirebaseFirestore.getInstance()

    fun getAllUniversitas(): CollectionReference {
        val docRef = database.collection("universitas")
        return docRef
    }

    fun getAllSoal(): CollectionReference {
        val docRef = database.collection("soals")
        return docRef
    }



}


//    private val mFirebaseDatabase = FirebaseDatabase.getInstance()


/*fun getDataFromDatabase(childName: String): Flowable<DataSnapshot> {
    return Flowable.create({ e ->
        database.child(childName)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    e.onNext(dataSnapshot)
                    e.onComplete()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
    }, BackpressureStrategy.BUFFER)
}*/

