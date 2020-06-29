package io.github.fuadreza.pikul_dagger.ui.main.univ

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.fuadreza.pikul_dagger.R

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 28/06/2020.
 *
 */

class UnivFragment: Fragment(){

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_univ, container, false)

        return view
    }
}