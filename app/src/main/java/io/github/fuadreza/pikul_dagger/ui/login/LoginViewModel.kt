package io.github.fuadreza.pikul_dagger.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.fuadreza.pikul_dagger.repository.user.UserManager
import javax.inject.Inject


/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

class LoginViewModel @Inject constructor(private val userManager: UserManager){

    private val _loginState = MutableLiveData<LoginViewState>()
    val loginState: LiveData<LoginViewState>
        get() = _loginState

    fun login(email: String, password: String) {
        if (userManager.loginUser(email,password)){
            _loginState.value = LoginSuccess
        }else {
            _loginState.value = LoginError
        }
    }

    //fun getUser(): FirebaseUser? = userManager.user
}