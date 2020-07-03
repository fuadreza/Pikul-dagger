package io.github.fuadreza.pikul_dagger.repository.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

@Singleton
class UserManager @Inject constructor(
    private val userComponentFactory: UserComponent.Factory
) {

    var userComponent: UserComponent? = null
        private set

    private val firebaseAuth = FirebaseAuth.getInstance()

    var user: FirebaseUser? = firebaseAuth.currentUser

    fun isUserLoggedIn(): Boolean {
        return user != null
    }

    fun userJustLoggedIn() {
        userComponent = userComponentFactory.create()
    }

    fun logoutUser(){
        firebaseAuth.signOut()
        user = null
        userComponent = null
    }
}


