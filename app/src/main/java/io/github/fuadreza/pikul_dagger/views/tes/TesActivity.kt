package io.github.fuadreza.pikul_dagger.views.tes

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.views.tes.detail_tes.DetailTesActivity
import kotlinx.android.synthetic.main.activity_tes.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 30/06/2020.
 *
 */
@AndroidEntryPoint
class TesActivity : AppCompatActivity() {

    private val tesViewModel: TesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes)

        lifecycle.addObserver(tesViewModel)

        setupViews()
    }

    private fun setupViews() {
        btn_1.setOnClickListener {
            val intent = Intent(this, DetailTesActivity::class.java)
            startActivity(intent)
        }
    }

}