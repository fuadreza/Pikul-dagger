package io.github.fuadreza.pikul_dagger.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.fuadreza.pikul_dagger.PikulApp
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.repository.user.UserManager
import io.github.fuadreza.pikul_dagger.ui.login.LoginActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {

        //(application as PikulApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        val userManager = (application as PikulApp).appComponent.userManager()

        if(!userManager.isUserLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }else {
            setContentView(R.layout.activity_main)
        }
    }
}