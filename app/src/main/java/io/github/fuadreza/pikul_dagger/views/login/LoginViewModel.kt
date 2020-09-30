package io.github.fuadreza.pikul_dagger.views.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import io.github.fuadreza.pikul_dagger.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

class LoginViewModel @ViewModelInject constructor(private val userRepository: UserRepository) :
    ViewModel(), LifecycleObserver {

    private var _isLoggedIn = false

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState>
        get() = _loginState

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun checkLogin() {
        viewModelScope.launch {
            _isLoggedIn = userRepository.isUserLoggedIn()
            if (_isLoggedIn) {
                _loginState.value = LoginState.IsLoggedIn
            }
        }
    }

    fun login(email: String, password: String) {
        /*if (userManager.loginUser(email, password)) {
            _loginState.value = LoginViewState.LoginSuccess
        } else {
            _loginState.value = LoginViewState.LoginError
        }*/

//        userManager.loginUser(email, password)

        _loginState.value = LoginState.IsLoading(true)

        if (validate(email, password)) {
            CoroutineScope(IO).launch {
                userRepository.login(email, password)
                if(userRepository.status.equals("success")){
                    _loginState.value = LoginState.IsLoggedIn
                }else{
                    _loginState.value = LoginState.LoginError
                }
            }

            /*FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    Log.d("LOGIN", "Login in...")
                }
                .addOnSuccessListener {
                    Log.d("LOGIN", "Success Login")
                    _loginState.value = LoginState.LoginSuccess
                    userRepository.user = it.user
                }
                .addOnFailureListener {
                    Log.d("LOGIN", "Failed Login: ${it.message}")
                    _loginState.value = LoginState.ShowToast("Email atau Password salah")
                    _loginState.value = LoginState.LoginError
                }*/

//            userRepository.userJustLoggedIn()
        }

        _loginState.value = LoginState.IsLoading(false)

    }

    private fun validate(email: String, password: String): Boolean {
        if (email.isBlank()) {
            _loginState.value = LoginState.ShowToast("Email tidak boleh kosong")
            return false
        } else {
            if (password.isBlank()) {
                _loginState.value = LoginState.ShowToast("Password tidak boleh kosong")
                return false
            }
        }
        return true
    }

    //fun getUser(): FirebaseUse r? = userManager.user
}


