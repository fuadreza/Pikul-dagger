package io.github.fuadreza.pikul_dagger.ui.main.tes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.ui.tes.TesActivity
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 28/06/2020.
 *
 */

class TesFragment : Fragment() {

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

    private fun setupViews(){
        btn_tes.setOnClickListener {
            startActivity(Intent(activity, TesActivity::class.java))
        }
    }
}