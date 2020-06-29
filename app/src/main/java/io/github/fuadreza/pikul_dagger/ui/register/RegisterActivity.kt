package io.github.fuadreza.pikul_dagger.ui.register

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.fuadreza.pikul_dagger.R

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 29/06/2020.
 *
 */

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }



    private fun toast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}