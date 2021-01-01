package io.github.fuadreza.pikul_dagger.views.tes.detail_tes

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
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

    private var userId: String? = null

    private var list_skor: ArrayList<Int>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tes)
        supportActionBar?.hide()

        list_skor = arrayListOf(0, 0, 0, 0, 0, 0)
        intent?.let {
            userId = it.getStringExtra(TesAdapter.EXTRA_UID)
            tes = it.getSerializableExtra(TesAdapter.EXTRA_TES) as Tes
            list_skor = it.getIntegerArrayListExtra(TesAdapter.EXTRA_SKOR)
        }

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
                    loading.hide()
                    Snackbar.make(
                        parentLayout,
                        state.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                    finish()
                }
                is DetailTesState.LoadingState -> {
                    btnLanjut.setBackgroundColor(Color.parseColor("#111111"))
                    Log.d("LOADING STATE", "LOADINGSTATE")
                    loading.visibility = View.VISIBLE
                    loading.show()
                }
                else -> {
                }
            }
        }
    }

    private fun btnHandler() {
        btnLanjut.setOnClickListener {
            btnLanjut.isClickable = false
            btnLanjut.setBackgroundColor(Color.parseColor("#aaaaaa"))
            saveScore()
            detailTesViewModel.nextSoal()
        }
    }

    private fun resetButtonState() {
        sbJawaban.progress = 2
        btnLanjut.isClickable = true
        btnLanjut.setBackgroundColor(resources.getColor(R.color.accent))
    }

    private fun saveScore() {
        val score = sbJawaban.progress
        detailTesViewModel.addScore(score)
    }

    private fun saveLastScore() {
        if (!list_skor.isNullOrEmpty()) {
            when (tes.type) {
                "1" -> {
                    skor?.let {
                        list_skor!![0] = it
                    }
                }
                "2" -> {
                    skor?.let {
                        list_skor!![1] = it
                    }
                }
                "3" -> {
                    skor?.let {
                        list_skor!![2] = it
                    }
                }
                "4" -> {
                    skor?.let {
                        list_skor!![3] = it
                    }
                }
                "5" -> {
                    skor?.let {
                        list_skor!![4] = it
                    }
                }
                "6" -> {
                    skor?.let {
                        list_skor!![5] = it
                    }
                }
                else -> {
                }
            }
            list_skor?.let {
                detailTesViewModel.saveUserScore(JawabanUser(userId.toString(), it))
            }
        }
    }

    private fun onLoadTes(soalTes: SoalTes?) {
//        Log.d("HELLO WORLD", "DATA RECEIVED : $soalList")
        soalTes?.let {
            tvSoal.text = soalTes.soal
        }
    }

}