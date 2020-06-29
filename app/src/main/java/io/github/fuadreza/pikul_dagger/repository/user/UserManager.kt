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

    fun loginUser(email: String, password: String) {
        /*_loginState.value = LoginViewState.IsLoading(true)
        //var login = true

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d("LOGIN", "Success Login")

            }
            .addOnFailureListener {
                Log.d("LOGIN", "Failed Login: ${it.message}")

            }

        _loginState.value = LoginViewState.IsLoading(false)

        userJustLoggedIn()
        //return login*/

    }

    fun userJustLoggedIn() {
        userComponent = userComponentFactory.create()
    }
}


