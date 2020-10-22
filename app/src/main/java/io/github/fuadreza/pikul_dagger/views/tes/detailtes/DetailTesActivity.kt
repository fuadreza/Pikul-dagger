package io.github.fuadreza.pikul_dagger.views.tes.detailtes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.model.SoalTes
import io.github.fuadreza.pikul_dagger.views.tes.TesViewModel
import io.github.fuadreza.pikul_dagger.views.tes.TesViewState
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 08/07/2020.
 *
 */
class DetailTesActivity : AppCompatActivity() {

    @Inject
    lateinit var tesViewModel: TesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

//        (application as PikulApp).appComponent.tesComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tes)
        supportActionBar?.hide()

        observeTesViewModel()

    }

    private fun observeTesViewModel() {
        tesViewModel.soalState.observe(this, Observer { state ->
            when (state) {
                is TesViewState.OnLoadUnivState -> onLoadTes(state.soalList)
            }
        })
    }

    private fun onLoadTes(soalList: List<SoalTes>) {

    }


}