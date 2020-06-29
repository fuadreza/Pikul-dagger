package io.github.fuadreza.pikul_dagger.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import io.github.fuadreza.pikul_dagger.repository.user.UserManager
import javax.inject.Inject


/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

class LoginViewModel @Inject constructor(private val userManager: UserManager) {

    private val _loginState = MutableLiveData<LoginViewState>()
    val loginState: LiveData<LoginViewState>
        get() = _loginState

    fun login(email: String, password: String) {
        /*if (userManager.loginUser(email, password)) {
            _loginState.value = LoginViewState.LoginSuccess
        } else {
            _loginState.value = LoginViewState.LoginError
        }*/

//        userManager.loginUser(email, password)

        _loginState.value = LoginViewState.IsLoading(true)

        if (validate(email,password)){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    Log.d("LOGIN", "Login in...")
                }
                .addOnSuccessListener {
                    Log.d("LOGIN", "Success Login")
                    _loginState.value = LoginViewState.LoginSuccess
                    userManager.user = it.user
                }
                .addOnFailureListener {
                    Log.d("LOGIN", "Failed Login: ${it.message}")
                    _loginState.value = LoginViewState.ShowToast("Email atau Password salah")
                    _loginState.value = LoginViewState.LoginError
                }

            userManager.userJustLoggedIn()
        }

        _loginState.value = LoginViewState.IsLoading(false)

    }

    fun validate(email: String, password: String): Boolean{
        if(email.isBlank()){
            _loginState.value = LoginViewState.ShowToast("Email tidak boleh kosong")
            return false
        }else {
            if(password.isBlank()){
                _loginState.value = LoginViewState.ShowToast("Password tidak boleh kosong")
                return false
            }
        }
        return true
    }

    //fun getUser(): FirebaseUse r? = userManager.user
}

sealed class LoginViewState {
    object LoginSuccess : LoginViewState()
    object LoginError : LoginViewState()

    data class ShowToast(var msg: String) : LoginViewState()
    data class IsLoading(var state: Boolean = false) : LoginViewState()
}
