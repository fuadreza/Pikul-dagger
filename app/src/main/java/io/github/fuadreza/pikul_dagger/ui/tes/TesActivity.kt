package io.github.fuadreza.pikul_dagger.ui.tes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.fuadreza.pikul_dagger.PikulApp
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.ui.tes.detailtes.DetailTesActivity
import kotlinx.android.synthetic.main.activity_tes.*
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 30/06/2020.
 *
 */

class TesActivity : AppCompatActivity() {

    @Inject
    lateinit var tesViewModel: TesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as PikulApp).appComponent.tesComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes)

        if(savedInstanceState == null) tesViewModel.getAllSoals()

        setupViews()
    }

    private fun setupViews(){
        btn_1.setOnClickListener {
            val intent = Intent(this, DetailTesActivity::class.java)
            startActivity(intent)
        }
    }

}