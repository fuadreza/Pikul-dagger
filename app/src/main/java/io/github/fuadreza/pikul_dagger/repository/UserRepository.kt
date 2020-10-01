package io.github.fuadreza.pikul_dagger.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

class UserRepository{

    private val firebaseAuth = FirebaseAuth.getInstance()

    var user: FirebaseUser? = firebaseAuth.currentUser

    var status: String = ""

    var registerStatus: String = ""

    fun isUserLoggedIn(): Boolean {
        return user != null
    }

    fun userJustLoggedIn() {
//        userComponent = userComponentFactory.create()
    }

    suspend fun login(email: String, password: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                user = it.user
                status = "success"
            }
            .addOnFailureListener {
                status = "Failed Login, ${it.message}"
            }
    }

    suspend fun register(name: String, email: String, password: String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                val user = FirebaseAuth.getInstance().currentUser
                val profileUpdate = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()

                user?.updateProfile(profileUpdate)
                registerStatus = "success"
            }
            .addOnFailureListener {
                registerStatus = "failed"
            }
    }

    fun logoutUser(){
        firebaseAuth.signOut()
        user = null
    }
}


