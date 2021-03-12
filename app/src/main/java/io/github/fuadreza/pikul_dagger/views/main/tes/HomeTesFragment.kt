package io.github.fuadreza.pikul_dagger.views.main.tes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.model.JawabanUser
import io.github.fuadreza.pikul_dagger.model.RekomendasiJurusan
import io.github.fuadreza.pikul_dagger.utils.getKategoriCode
import io.github.fuadreza.pikul_dagger.views.tes.TesWarningActivity
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 28/06/2020.
 *
 */

@AndroidEntryPoint
class HomeTesFragment : Fragment(), LifecycleOwner {

    private val viewModel: HomeTesViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_test, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()


    }

    private fun setupViews() {
        btn_tes.setOnClickListener {
            startActivity(Intent(activity, TesWarningActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        observeUserProgress()
        observeRekomendasi()
    }

    private fun observeUserProgress() {
        viewModel.userProgress.observe(this){userProgress ->
            userProgress.let {
                setupScore(it)
                val userKategoriSkor = getKategoriCode(userProgress.skor_kat)
                viewModel.fetchRekomendasi(userKategoriSkor.toString())
            }
        }
    }

    private fun observeRekomendasi() {
        viewModel.userRekomendasiJurusan.observe(this){
            setupRekomendasi(it)
        }
    }

    private fun setupRekomendasi(listRekomendasi: ArrayList<RekomendasiJurusan>) {
        //TODO display rekomendasi jurusan in list
    }

    private fun setupScore(progress: JawabanUser) {
        //TODO Display score on bar and number
    }
}