package io.github.fuadreza.pikul_dagger.views.login

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 01/10/2020.
 *
 */
sealed class LoginState {
    object LoginSuccess : LoginState()
    object LoginError : LoginState()
    object IsLoggedIn : LoginState()

    data class ShowToast(var msg: String) : LoginState()
    data class IsLoading(var state: Boolean = false) : LoginState()
}