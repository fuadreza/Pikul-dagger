package io.github.fuadreza.pikul_dagger.data.remote

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */

class UnivFirestore constructor() {

    private val firestore = FirebaseFirestore.getInstance()

    fun getAllUniversitas(): Query {
        val docRef = firestore.collection("universitas")
            .orderBy("id")
            .limit(25)
        return docRef
    }

    fun getAllUniv(): CollectionReference {
        val docRef = firestore.collection("universitas")
        return docRef
    }

    fun getAllSoal(): CollectionReference {
        val docRef = firestore.collection("soals")
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

