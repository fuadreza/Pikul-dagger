package io.github.fuadreza.pikul_dagger.views.tes.detail_tes

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.model.SoalTes
import io.github.fuadreza.pikul_dagger.views.tes.TesViewModel
import io.github.fuadreza.pikul_dagger.views.tes.TesViewState
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 08/07/2020.
 *
 */
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