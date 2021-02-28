package io.github.fuadreza.pikul_dagger.views.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.views.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 29/06/2020.
 *
 */
@AndroidEntryPoint
class RegisterActivity : AppCompatActivity(), LifecycleOwner {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO tampilan register
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        lifecycle.addObserver(registerViewModel)

        setupViews()
    }

    override fun onResume() {
        super.onResume()

        observe()
    }

    private fun observe() {
        registerViewModel.registerState.observe(this, Observer<RegisterState> { state ->
            handleUIState(state)
        })
    }

    private fun setupViews() {
        btn_register.setOnClickListener {
            registerViewModel.register(
                ed_first.text.toString(),
                ed_last.text.toString(),
                ed_email.text.toString(),
                ed_password.text.toString(),
                ed_confirm_password.text.toString()
            )
        }
        btn_sign_in.setOnClickListener{
            startActivity(
                Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            )
            finish()
        }
    }

    private fun handleUIState(it: RegisterState) {
        when (it) {
            is RegisterState.RegisterSuccess -> {
                registerViewModel.saveUser(
                    ed_first.text.toString(),
                    ed_last.text.toString(),
                    ed_email.text.toString()
                )
            }
            is RegisterState.RegisterError -> {
                isLoading(false)
            }
            is RegisterState.SaveUserSuccess -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            is RegisterState.SaveUserError -> {
                isLoading(false)
            }
            is RegisterState.ShowToast -> {
                toast(it.msg)
            }
            is RegisterState.IsLoading -> isLoading(it.state)
        }
    }

    private fun isLoading(state: Boolean) {
        // TODO Loading Not implemented
        if(state) loading.visibility = View.VISIBLE
        else loading.visibility = View.GONE
    }

    private fun toast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}