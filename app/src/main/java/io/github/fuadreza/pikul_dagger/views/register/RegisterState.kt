package io.github.fuadreza.pikul_dagger.views.register

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 01/10/2020.
 *
 */

sealed class RegisterState {
    object RegisterSuccess : RegisterState()
    object RegisterError : RegisterState()
    object SaveUserSuccess: RegisterState()
    object SaveUserError : RegisterState()

    data class ShowToast(var msg: String) : RegisterState()
    data class IsLoading(var state: Boolean = false) : RegisterState()
}