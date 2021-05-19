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
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
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

    private val rekomendasiJurusanAdapter = GroupAdapter<ViewHolder>()

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_test, container, false)

        lifecycle.addObserver(viewModel)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        rv_rekomendasi.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rekomendasiJurusanAdapter
        }
        btn_tes.setOnClickListener {
            startActivity(Intent(activity, TesWarningActivity::class.java))
        }
        scoreContainerEnabled(false)
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
                if(userProgress.skor_kat[5] != 0 && userProgress.skor_kat[4] != 0 && userProgress.skor_kat[3] != 0) {
                    val userKategoriSkor = getKategoriCode(userProgress.skor_kat)
                    viewModel.fetchRekomendasi(userKategoriSkor.toString())
                }
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
        scoreContainerEnabled(true)
        rekomendasiJurusanAdapter.clear()
        listRekomendasi[0].rekomendasi.forEach {
            rekomendasiJurusanAdapter.add(RekomendasiJurusanAdapter(it.toString()))
        }
        rekomendasiJurusanAdapter.notifyDataSetChanged()
    }

    private fun setupScore(progress: JawabanUser) {
        //TODO Display score on bar and number
    }

    private fun scoreContainerEnabled(state: Boolean){
        if(state){
            rv_rekomendasi.visibility = View.VISIBLE
            tv_tes_jurusan.visibility = View.GONE
            iv_icon_kerjakan.visibility = View.GONE
        }else {
            rv_rekomendasi.visibility = View.GONE
            tv_tes_jurusan.visibility = View.VISIBLE
            iv_icon_kerjakan.visibility = View.VISIBLE
        }
    }
}