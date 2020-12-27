package io.github.fuadreza.pikul_dagger.views.tes.detail_tes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.model.JawabanUser
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
// [v] save score per soal (save with soal id) <- save only score
// [v] save score to firestore
// [v] Next question
// [o] go to hasil tes if all finished <- change schema (display button hasil di tes activity)
// [v] back to tes activity when finished
// [v] reset progress after next soal
// [ ] display loading while save score
// Pending local database
// [ ] Save answer on session <- pending
// [o] Save last score to local database
// [o] Save user progress to local database

@AndroidEntryPoint
class DetailTesActivity : AppCompatActivity() {

    private val detailTesViewModel: DetailTesViewModel by viewModels()

    private lateinit var tes: Tes

    private var skor: Int? = null

    private var list_skor: ArrayList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tes)
        supportActionBar?.hide()

        intent?.let {
            tes = it.getSerializableExtra(TesAdapter.EXTRA_TES) as Tes
        }
        list_skor = arrayListOf(0, 0, 0, 0, 0, 0)
        lifecycle.addObserver(detailTesViewModel)

        btnHandler()
    }

    override fun onResume() {
        super.onResume()
        observeTesViewModel()
    }

    private fun observeTesViewModel() {
        detailTesViewModel.totalSkor.observe(this) {
            skor = it
        }

        detailTesViewModel.soalIndex.observe(this) { index ->
            resetButtonState()
            tes.questions?.let { questions ->
                if (index < questions.size) {
                    tes.questions?.get(index)?.let {
                        detailTesViewModel.getSoalById(it)
                    }
                } else {
                    saveLastScore()
                    btnLanjut.isClickable = false
//                    finish()
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

        detailTesViewModel.savedScoreState.observe(this) { state ->
            when (state) {
                is DetailTesState.OnSavedScoreState -> {
                    Snackbar.make(
                        parentLayout,
                        state.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                    finish()
                }
                else -> {
                }
            }
        }
    }

    private fun btnHandler() {
        btnLanjut.setOnClickListener {
            btnLanjut.isClickable = false
            saveScore()
            detailTesViewModel.nextSoal()
        }
    }

    private fun resetButtonState(){
        sbJawaban.progress = 2
        btnLanjut.isClickable = true
    }

    private fun saveScore() {
        val score = sbJawaban.progress
        detailTesViewModel.addScore(score)
    }

    private fun saveLastScore() {
        //TODO handle save score
        if (!list_skor.isNullOrEmpty()) {
            when (tes.type) {
                "R" -> {
                    skor?.let {
                        list_skor[0] = it
                    }
                }
                "I" -> {
                    skor?.let {
                        list_skor[1] = it
                    }
                }
                "A" -> {
                    skor?.let {
                        list_skor[2] = it
                    }
                }
                "S" -> {
                    skor?.let {
                        list_skor[3] = it
                    }
                }
                "E" -> {
                    skor?.let {
                        list_skor[4] = it
                    }
                }
                "C" -> {
                    skor?.let {
                        list_skor[5] = it
                    }
                }
                else -> {
                }
            }
            detailTesViewModel.saveUserScore(JawabanUser("awaaw", list_skor))
        }
    }

    private fun onLoadTes(soalTes: SoalTes?) {
//        Log.d("HELLO WORLD", "DATA RECEIVED : $soalList")
        soalTes?.let {
            tvSoal.text = soalTes.soal
        }
    }

}