package io.github.fuadreza.pikul_dagger.repository.user

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

class UserManager @Inject constructor(
    private val userComponentFactory: UserComponent.Factory
) {

    var userComponent: UserComponent? = null
        private set

    val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    fun isUserLoggedIn() = userComponent != null

    fun registerUser(name: String, email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                val user = FirebaseAuth.getInstance().currentUser
                val profileUpdate = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()

                user?.updateProfile(profileUpdate)
            }.addOnFailureListener {
                Log.d("REGISTER", "Gagal membuat akun: ${it.message}")

            }
    }

    fun loginUser(email: String, password: String): Boolean {
        var login: Boolean = false
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                login = true
            }
            .addOnFailureListener {
                login  =false
                Log.d("LOGIN", "Failed Login: ${it.message}")
            }

        userJustLoggedIn()
        return login
    }

    private fun userJustLoggedIn() {
        userComponent = userComponentFactory.create()
    }
}