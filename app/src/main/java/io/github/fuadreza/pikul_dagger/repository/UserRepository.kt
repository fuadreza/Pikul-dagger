package io.github.fuadreza.pikul_dagger.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import io.github.fuadreza.pikul_dagger.model.UserProfile
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

class UserRepository @Inject constructor(private val auth: FirebaseAuth, private val db: FirebaseFirestore) {

    var user: FirebaseUser? = auth.currentUser

    var status: String = ""

    var registerStatus: String = ""

    var uid: String = ""

    fun isUserLoggedIn(): Boolean {
        return user != null
    }

    fun userJustLoggedIn() {
//        userComponent = userComponentFactory.create()
    }

    suspend fun login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                user = it.user
                status = "success"
            }
            .addOnFailureListener {
                status = "Failed Login, ${it.message}"
            }
    }

    suspend fun register(name: String, email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                val user = FirebaseAuth.getInstance().currentUser
                val profileUpdate = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()

                user?.updateProfile(profileUpdate)
                uid = user?.uid.toString()
                registerStatus = "success"
            }
            .addOnFailureListener {
                registerStatus = "failed"
            }
    }

    fun saveUserData(userProfile: UserProfile) : Task<Void>{
        val user = auth.currentUser
        return db.collection("users").document("aaaa").set(UserProfile(uid, userProfile.firstName, userProfile.lastName, userProfile.email, userProfile.urlPic))
    }

    fun logoutUser() {
        auth.signOut()
        user = null
    }
}


