package io.github.fuadreza.pikul_dagger.views.tes.detail_tes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.model.SoalTes

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 08/07/2020.
 *
 */
//TODO Detail tes
// [ ] Halaman detail tes
// [ ] Get data from Firebase
// [ ] Save answer on session
// [ ] Next question
// [ ] Save last score to local database

@AndroidEntryPoint
class DetailTesActivity : AppCompatActivity() {

    private val detailTesViewModel: DetailTesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tes)
        supportActionBar?.hide()

        lifecycle.addObserver(detailTesViewModel)

        detailTesViewModel.getSoalsByCategory("R")

        observeTesViewModel()

    }

    private fun observeTesViewModel() {
        detailTesViewModel.soalState.observe(this, Observer { state ->
            when (state) {
                is DetailTesState.OnLoadSoalState -> onLoadTes(state.soalList)
            }
        })
    }

    private fun onLoadTes(soalList: List<SoalTes>) {
        //TODO ABLE TO GET DATA
        // IMPLEMENT tes function (display soal and save score)
//        Log.d("HELLO WORLD", "DATA RECEIVED : $soalList")
    }


}