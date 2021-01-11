package io.github.fuadreza.pikul_dagger.views.tes.hasil_tes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R

//TODO Hasil Tes
// [v] Halaman Hasil Tes
// [ ] Get User Score
// [ ] Sort top 3 kategori
// [ ] Get recommendation based on top 3
// [ ] Display recommendation

@AndroidEntryPoint
class HasilTesActivity: AppCompatActivity() {

    private val viewModel : HasilTesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_tes)
        supportActionBar?.hide()

        lifecycle.addObserver(viewModel)

        btnHandler()
    }

    override fun onResume() {
        super.onResume()
        observeUserScoer()
        observeRekomendasi()
    }

    private fun observeUserScoer() {

    }

    private fun observeRekomendasi() {

    }

    private fun btnHandler() {

    }
}