package io.github.fuadreza.pikul_dagger.views.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.views.main.HomeActivity
import io.github.fuadreza.pikul_dagger.views.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), LifecycleOwner {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        lifecycle.addObserver(loginViewModel)

        setupViews()
    }

    override fun onResume() {
        super.onResume()
        observe()
    }

    private fun observe() {
        loginViewModel.loginState.observe(this, Observer<LoginState> { state ->
            handleUIState(state)
        })
    }

    private fun setupViews() {
        btn_login.setOnClickListener {
            loginViewModel.login(edEmail.text.toString(), edPassword.text.toString())
        }
        tv_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun handleUIState(it: LoginState?) {
        when (it) {
            is LoginState.IsLoggedIn -> {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            is LoginState.LoginSuccess -> {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            is LoginState.LoginError -> {
                isLoading(false)
            }
            is LoginState.ShowToast -> {
                toast(it.msg)
            }
            is LoginState.IsLoading -> isLoading(it.state)
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

