package io.github.fuadreza.pikul_dagger.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import io.github.fuadreza.pikul_dagger.repository.user.UserManager
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 29/06/2020.
 *
 */

class RegisterViewModel @Inject constructor(private val userManager: UserManager) {

    private val _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState>
        get() = _registerState

    fun register(name: String, email: String, password: String, confirmPassword: String) {
        _registerState.value = RegisterState.IsLoading(true)

        if (validate(name, email, password, confirmPassword)) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    val user = FirebaseAuth.getInstance().currentUser
                    val profileUpdate = UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()

                    user?.updateProfile(profileUpdate)

                    _registerState.value = RegisterState.RegisterSuccess
                    _registerState.value = RegisterState.ShowToast("Akun berhasil dibuat")

                }.addOnFailureListener {
                    Log.d("REGISTER", "Gagal membuat akun: ${it.message}")
                    _registerState.value = RegisterState.ShowToast("Gagal membuat akun")
                    _registerState.value = RegisterState.RegisterError
                }
        }

        _registerState.value = RegisterState.IsLoading(false)

    }

    fun validate(name: String, email: String, password: String, confirmPassword: String): Boolean {

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

sealed class RegisterState {
    object RegisterSuccess : RegisterState()
    object RegisterError : RegisterState()

    data class ShowToast(var msg: String) : RegisterState()
    data class IsLoading(var state: Boolean = false) : RegisterState()
}