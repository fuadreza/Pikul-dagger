package io.github.fuadreza.pikul_dagger.views.tes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.fuadreza.pikul_dagger.R
import kotlinx.android.synthetic.main.activity_warning.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 12/08/2020.
 *
 */

class TesWarningActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warning)
        supportActionBar?.hide()

        btn_mengerti.setOnClickListener{
            val intent = Intent(this, TesActivity::class.java)
            startActivity(intent)
        }

        
    }
}