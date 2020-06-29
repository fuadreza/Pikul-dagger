package io.github.fuadreza.pikul_dagger.ui.main.profile

import android.content.Context
import android.os.Bundle
import android.os.UserManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseUser
import io.github.fuadreza.pikul_dagger.PikulApp
import io.github.fuadreza.pikul_dagger.R
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.Serializable

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 28/06/2020.
 *
 */

class ProfileFragment: Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val user : FirebaseUser? = arguments?.getParcelable("user")

        val userManager = (activity?.application as PikulApp).appComponent.userManager()

        Log.d("PAKET" , "USER " + userManager.user?.displayName)

        setupViews(userManager.user)
    }

    private fun setupViews(user: FirebaseUser?) {
        tv_nama.text = user?.displayName
        tv_email.text = user?.email
    }

}