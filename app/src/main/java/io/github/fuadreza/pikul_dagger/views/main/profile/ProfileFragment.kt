package io.github.fuadreza.pikul_dagger.views.main.profile

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
import io.github.fuadreza.pikul_dagger.model.UserProfile
import io.github.fuadreza.pikul_dagger.utils.toast
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 28/06/2020.
 *
 */

@AndroidEntryPoint
class ProfileFragment : Fragment(), LifecycleOwner {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        lifecycle.addObserver(viewModel)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.profileState.observe(viewLifecycleOwner) {
            when (it) {
                is ProfileState.ProfileLoaded -> {
                    setupProfile(it.data)
                }
                is ProfileState.ProgressLoaded -> {
                    if(it.data.skor_kat[5] != 0) setupProgress(it.data)
                }
                is ProfileState.LoadProfileError -> {
                    toast("Data gagal diunduh")
                }
                is ProfileState.LoadProgressError -> {
                    stateScoreFinished(false)
                }
                is ProfileState.LoadingState -> {

                }
                else -> {

                }
            }
        }
    }

    private fun setupProfile(user: UserProfile?) {
        tv_nama.text = "${user?.firstName} ${user?.lastName}"
        tv_email.text = user?.email

//        btn_edit.setOnClickListener {
//            startActivity(Intent(view.context, SettingActivity::class.java))
//        }
    }

    private fun setupProgress(progress: JawabanUser?) {
        stateScoreFinished(true)

        progress?.let {
            tv_kategori_r.text = it.skor_kat[0].toString()
            tv_kategori_i.text = it.skor_kat[1].toString()
            tv_kategori_a.text = it.skor_kat[2].toString()
            tv_kategori_s.text = it.skor_kat[3].toString()
            tv_kategori_e.text = it.skor_kat[4].toString()
            tv_kategori_c.text = it.skor_kat[5].toString()
        }
    }

    private fun stateScoreFinished(state: Boolean) {
        if(state){
            tv_no_score.visibility = View.GONE
            container_score.visibility = View.VISIBLE
        }else {
            tv_no_score.visibility = View.VISIBLE
            container_score.visibility = View.GONE
        }
    }

}