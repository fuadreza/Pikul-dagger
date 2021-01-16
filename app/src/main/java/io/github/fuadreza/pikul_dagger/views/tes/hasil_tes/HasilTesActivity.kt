package io.github.fuadreza.pikul_dagger.views.tes.hasil_tes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.model.JawabanUser
import kotlinx.android.synthetic.main.activity_hasil_tes.*

//TODO Hasil Tes
// [v] Halaman Hasil Tes
// [v] Get User Score
// [ ] Sort top 3 kategori
// [ ] Get recommendation based on top 3
// [ ] Display recommendation

@AndroidEntryPoint
class HasilTesActivity: AppCompatActivity() {

    private val viewModel : HasilTesViewModel by viewModels()

    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_tes)
        supportActionBar?.hide()

        lifecycle.addObserver(viewModel)

        btnHandler()
    }

    override fun onResume() {
        super.onResume()
        observeUserScore()
        observeRekomendasi()
    }

    private fun observeUserScore() {
        viewModel.userId.observe(this) {
            viewModel.fetchUserProgress(it)
            userId = it
        }
        viewModel.userProgress.observe(this){progress ->
            setupScore(progress)
        }
    }

    private fun setupScore(score: JawabanUser) {
        score.skor_kat.forEachIndexed{index, it ->
            when(index){
                0-> tv_kategori_r.text = it.toString()
                1 -> tv_kategori_i.text = it.toString()
                2 -> tv_kategori_a.text = it.toString()
                3 -> tv_kategori_s.text = it.toString()
                4 -> tv_kategori_e.text = it.toString()
                5 -> tv_kategori_c.text = it.toString()
                else ->{}
            }
        }
    }

    private fun observeRekomendasi() {

    }

    private fun btnHandler() {

    }
}