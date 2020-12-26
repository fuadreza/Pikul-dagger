package io.github.fuadreza.pikul_dagger.views.tes.detail_tes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.model.SoalTes
import io.github.fuadreza.pikul_dagger.views.tes.TesAdapter
import io.github.fuadreza.pikul_dagger.views.tes.model.Tes
import kotlinx.android.synthetic.main.activity_detail_tes.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 08/07/2020.
 *
 */
//TODO Detail tes
// [v] Halaman detail tes
// [o] Get data from Firebase
// [v] display soal
// [v] get soal by id soal
// [ ] Save answer on session
// [ ] save score per soal (save with soal id)
// [v] Next question
// [ ] Save last score to local database
// [ ] Save user progress to local database
// [ ] back to tes activity when finished
// [ ] go to hasil tes if all finished

@AndroidEntryPoint
class DetailTesActivity : AppCompatActivity() {

    private val detailTesViewModel: DetailTesViewModel by viewModels()

    private lateinit var tes: Tes

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tes)
        supportActionBar?.hide()

        intent?.let {
            tes = it.getSerializableExtra(TesAdapter.EXTRA_TES) as Tes
        }

        lifecycle.addObserver(detailTesViewModel)

        btnHandler()
    }

    override fun onResume() {
        super.onResume()
        observeTesViewModel()
    }

    private fun observeTesViewModel() {
        detailTesViewModel.soalIndex.observe(this) { index ->
            tes.questions?.let { questions ->
                if (index < questions.size) {
                    tes.questions?.get(index)?.let {
                        detailTesViewModel.getSoalById(it)
                    }
                }else {
                    Toast.makeText(this, "Last soal", Toast.LENGTH_LONG).show()
                }
            }
        }

        detailTesViewModel.soalState.observe(this, Observer { state ->
            when (state) {
                is DetailTesState.OnLoadSoalState -> onLoadTes(state.soalList)
                else -> {
                }
            }
        })
    }

    private fun btnHandler() {
        btnLanjut.setOnClickListener {
            detailTesViewModel.nextSoal()
        }
    }

    private fun onLoadTes(soalTes: SoalTes?) {
//        Log.d("HELLO WORLD", "DATA RECEIVED : $soalList")
        soalTes?.let {
            tvSoal.text = soalTes.soal
        }
    }

}