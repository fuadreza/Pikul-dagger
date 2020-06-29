package io.github.fuadreza.pikul_dagger.repository.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import io.github.fuadreza.pikul_dagger.ui.login.LoginViewModel
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

    var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    fun isUserLoggedIn() = userComponent != null

    fun userJustLoggedIn() {
        userComponent = userComponentFactory.create()
    }
}


