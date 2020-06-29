package io.github.fuadreza.pikul_dagger.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.github.fuadreza.pikul_dagger.PikulApp
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.ui.main.MainActivity
import io.github.fuadreza.pikul_dagger.ui.main.MainViewModel
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    /*@Inject
    lateinit var userManager: UserManager*/

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as PikulApp).appComponent.loginComponent().create().inject(this)

        //val userManager = (application as PikulApp).appComponent.userManager()
        //userManager.userComponent!!.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        /* loginViewModel.loginState.observe(this, Observer<LoginViewState> { state ->
             handleUIState(state)
         })*/

        loginViewModel.loginState.observe(this, Observer<LoginViewState> { state ->
            handleUIState(state)
        })

        setupViews()
    }

    private fun setupViews() {
        btn_login.setOnClickListener {
            loginViewModel.login(edEmail.text.toString(), edPassword.text.toString())
        }
    }

    private fun handleUIState(it: LoginViewState?) {
        when (it) {
            is LoginViewState.LoginSuccess -> {
                Log.e("MASUK", "SUKSES")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            is LoginViewState.LoginError -> {
                Log.e("MASUK", "ERROR")
                isLoading(false)
            }
            is LoginViewState.ShowToast -> {
                toast(it.msg)
            }
            is LoginViewState.IsLoading -> isLoading(it.state)
        }
    }

    private fun isLoading(state: Boolean) {
        if (state) {
            btn_login.isEnabled = false
            loading.isIndeterminate = true
        } else {
            btn_login.isEnabled = true
            loading.apply {
                isIndeterminate = false
                progress = 0
            }
        }
    }

    private fun toast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

