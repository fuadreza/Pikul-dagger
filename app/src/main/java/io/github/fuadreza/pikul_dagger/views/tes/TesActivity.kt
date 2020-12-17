package io.github.fuadreza.pikul_dagger.views.tes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
// [ ] Enable and disable button based on Progress
// [ ] Load progress from database

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
        tesViewModel.tes.observe(this, Observer { tes ->
            adapter.setTes(tes)
        })
        tesViewModel.userProgress.observe(this, Observer { userProgress ->
            //TODO SET PROGRESS TO BUTTON TES
        })
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