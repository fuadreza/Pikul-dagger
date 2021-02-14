package io.github.fuadreza.pikul_dagger.views.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import io.github.fuadreza.pikul_dagger.model.UserProfile
import io.github.fuadreza.pikul_dagger.repository.UserRepository

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 29/06/2020.
 *
 */

class RegisterViewModel @ViewModelInject constructor(private val userRepository: UserRepository) :
    ViewModel(), LifecycleObserver {

    private val _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState>
        get() = _registerState

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        _registerState.value = RegisterState.IsLoading(true)

        if (validate(firstName, email, password, confirmPassword)) {
                userRepository.register("$firstName $lastName", email, password)
                    .addOnSuccessListener {
                        // Update profile
                        val user = FirebaseAuth.getInstance().currentUser
                        val profileUpdate = UserProfileChangeRequest.Builder()
                            .setDisplayName("$firstName $lastName")
                            .build()
                        user?.updateProfile(profileUpdate)

                        _registerState.postValue(RegisterState.RegisterSuccess)
                    }
                    .addOnFailureListener {
                        _registerState.postValue(RegisterState.ShowToast("Gagal mendaftarkan akun"))
                        _registerState.postValue(RegisterState.RegisterError)
                    }
        }
        _registerState.value = RegisterState.IsLoading(false)
    }

    fun saveUser(firstName: String, lastName: String, email: String) {
        _registerState.value = RegisterState.IsLoading(true)
        userRepository.saveUserData(UserProfile("", firstName, lastName, email, ""))
            .addOnSuccessListener {
                _registerState.postValue(RegisterState.SaveUserSuccess)
            }
            .addOnFailureListener {
                it.printStackTrace()
                _registerState.postValue(RegisterState.SaveUserError)
            }
        _registerState.value = RegisterState.IsLoading(false)
    }

    private fun validate(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
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