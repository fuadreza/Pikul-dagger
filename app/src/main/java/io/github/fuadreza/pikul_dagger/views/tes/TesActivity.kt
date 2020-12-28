package io.github.fuadreza.pikul_dagger.views.tes

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes)
        supportActionBar?.title = "Tes"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        lifecycle.addObserver(tesViewModel)

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
        }
        tesViewModel.userProgress.observe(this, Observer { userProgress ->
            userProgress?.let {
//                adapter.setUserProgress(it.progress!!.toInt())
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

    private fun setupViews() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}