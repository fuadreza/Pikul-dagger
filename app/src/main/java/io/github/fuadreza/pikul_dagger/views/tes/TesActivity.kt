package io.github.fuadreza.pikul_dagger.views.tes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.model.JawabanUser
import kotlinx.android.synthetic.main.activity_tes.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 30/06/2020.
 *
 */

//TODO Tes activity
// [v] Fix screen
// [v] Adapter 6 menu
// [v] Onclick menu
// [v] Enable and disable button based on Progress
// [v] Load progress from database
// [v] Save Progress after clear tes
// [v] Refactor Tes include list id of question
// [v] Load user progress from firebase
// [ ] button state based on user progress
// [ ] Use model UserProgress to save to Firebase

@AndroidEntryPoint
class TesActivity : AppCompatActivity() {

    private val tesViewModel: TesViewModel by viewModels()

    private lateinit var adapter: TesAdapter

    private var list_skor: ArrayList<Int> = arrayListOf()

    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes)
        supportActionBar?.title = "Tes"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        lifecycle.addObserver(tesViewModel)
        list_skor = arrayListOf(0, 0, 0, 0, 0, 0)

        setupViews()

        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        observe()
    }

    private fun observe() {
        tesViewModel.userId.observe(this){
            tesViewModel.fetchUserProgress(it)
            userId = it
        }
        tesViewModel.userProgress.observe(this, Observer { userProgress ->
            userProgress?.let {
                adapter.setUserProgress(getUserProgress(it), userProgress.skor_kat, userId)
            }
        })
        tesViewModel.tes.observe(this, Observer { tes ->
            adapter.setTes(tes)
        })
        tesViewModel.userProgress.observe(this){
            //Data received
        }
    }

    private fun initAdapter() {
        adapter = TesAdapter(this)
        rv_tes.layoutManager = GridLayoutManager(this, 3)
        rv_tes.adapter = adapter
    }

    private fun getUserProgress(it: JawabanUser): Int {
        it.skor_kat.forEachIndexed {index, skor ->
            if(skor==0) return index
        }
        return 0
    }

    private fun setupViews() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}