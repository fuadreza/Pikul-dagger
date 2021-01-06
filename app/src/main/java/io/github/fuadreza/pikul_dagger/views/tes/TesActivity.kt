package io.github.fuadreza.pikul_dagger.views.tes

import android.os.Bundle
import android.view.View
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
// [v] button state based on user progress
// [v] Use model UserProgress to save to Firebase
// [v] Show/Hide Button hasil tes
// [v] Checked for answered tes
// [ ] Re-test button

@AndroidEntryPoint
class TesActivity : AppCompatActivity() {

    private val tesViewModel: TesViewModel by viewModels()

    private lateinit var adapter: TesAdapter

    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes)
        supportActionBar?.title = "Tes"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        lifecycle.addObserver(tesViewModel)

        setupViews()

        initAdapter()

        buttonHandler()
    }

    override fun onResume() {
        super.onResume()
        observe()
    }

    private fun buttonHandler() {
        btn_hasil.setOnClickListener {
            //TODO goto hasil_activity
        }
    }

    private fun observe() {
        tesViewModel.userId.observe(this){
            tesViewModel.fetchUserProgress(it)
            userId = it
        }
        tesViewModel.userProgress.observe(this, Observer { userProgress ->
            userProgress?.let {
                adapter.setUserProgress(getUserProgress(it), userProgress.skor_kat, userId)
                toggleButtonHasil(it)
                if(it.skor_kat[5] != 0){
                    btn_hasil.visibility = View.VISIBLE
                }else{
                    btn_hasil.visibility = View.INVISIBLE
                }
            }
        })
        tesViewModel.tes.observe(this, Observer { tes ->
            adapter.setTes(tes)
        })
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
        return 6
    }

    private fun toggleButtonHasil(it: JawabanUser){
        if (it.skor_kat[5] != 0)
            btn_hasil.visibility = View.VISIBLE
        else
            btn_hasil.visibility = View.INVISIBLE
    }

    private fun setupViews() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}