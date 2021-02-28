package io.github.fuadreza.pikul_dagger.views.main.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.model.UserProfile
import io.github.fuadreza.pikul_dagger.utils.toast
import io.github.fuadreza.pikul_dagger.views.setting.SettingActivity
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
        viewModel.profileState.observe(viewLifecycleOwner){
            when(it){
                is ProfileState.ProfileLoaded -> {
                    setupViews(it.data)
                }
                is ProfileState.LoadProfileError -> {
                    toast("Data gagal diunduh")
                }
                is ProfileState.LoadingState -> {

                }
                else -> {

                }
            }
        }
    }

    private fun setupViews(user: UserProfile?) {
        tv_nama.text = "${user?.firstName} ${user?.lastName}"
        tv_email.text = user?.email

//        btn_edit.setOnClickListener {
//            startActivity(Intent(view.context, SettingActivity::class.java))
//        }
    }

}