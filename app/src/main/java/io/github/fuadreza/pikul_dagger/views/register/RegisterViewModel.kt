package io.github.fuadreza.pikul_dagger.views.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.fuadreza.pikul_dagger.model.UserProfile
import io.github.fuadreza.pikul_dagger.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 29/06/2020.
 *
 */

class RegisterViewModel @ViewModelInject constructor(private val userRepository: UserRepository) :
    ViewModel(), LifecycleObserver {

    private val _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState>
        get() = _registerState

    fun register(firstName: String, lastName: String, email: String, password: String, confirmPassword: String) {
        _registerState.value = RegisterState.IsLoading(true)

        if (validate(firstName, email, password, confirmPassword)) {
            CoroutineScope(Dispatchers.IO).launch {
                userRepository.register("$firstName $lastName", email, password)
                if (userRepository.registerStatus == "success") {
                    _registerState.postValue(RegisterState.RegisterSuccess)
                } else {
                    _registerState.postValue(RegisterState.ShowToast("Gagal mendaftarkan akun"))
                    _registerState.postValue(RegisterState.RegisterError)
                }
            }
        }
        _registerState.value = RegisterState.IsLoading(false)
    }

    fun saveUser(firstName: String, lastName: String, email: String){
        userRepository.saveUserData(UserProfile("", firstName, lastName, email, ""))
            .addOnSuccessListener {
                _registerState.postValue(RegisterState.SaveUserSuccess)
                _registerState.postValue(RegisterState.ShowToast("Akun berhasil dibuat"))
            }
            .addOnFailureListener{
                it.printStackTrace()
                _registerState.postValue(RegisterState.ShowToast("Gagal menyimpan akun"))
                _registerState.postValue(RegisterState.SaveUserError)
            }
    }

    private fun validate(name: String, email: String, password: String, confirmPassword: String): Boolean {
        if (name.isBlank()) {
            _registerState.value = RegisterState.ShowToast("Nama tidak boleh kosong")
        } else {
            if (email.isBlank()) {
                _registerState.value = RegisterState.ShowToast("Email tidak boleh kosong")
            } else {
                if (password.isBlank() || confirmPassword.isBlank()) {
                    _registerState.value = RegisterState.ShowToast("Password tidak boleh kosong")
                } else {
                    if (password == confirmPassword) {
                        return true
                    } else {
                        _registerState.value =
                            RegisterState.ShowToast("Password harus sama")
                    }
                }
            }
        }
        return false
    }

}