package io.github.fuadreza.pikul_dagger.ui.login

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import io.github.fuadreza.pikul_dagger.PikulApp
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

        (application as PikulApp).appComponent.loginComponent().create().inject(this)

        super.onCreate(savedInstanceState, persistentState)
    }
}

sealed class LoginViewState
object LoginSuccess : LoginViewState()
object LoginError : LoginViewState()