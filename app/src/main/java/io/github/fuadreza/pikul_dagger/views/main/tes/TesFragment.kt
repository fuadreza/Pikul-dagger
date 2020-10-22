package io.github.fuadreza.pikul_dagger.views.main.tes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.views.tes.TesWarningActivity
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

        /*val storage = Firebase.storage

        val storageRef = storage.reference

        val logoRef = storageRef.child("logo_univ/ugm.png")

        val localFile = File.createTempFile("ugm", "png")*/

        /*var file = File(context?.filesDir, "logo_univ")
        if (!file.exists()) {
            file.mkdir()
        }
        file = File(file, "ugm.png")
        val out = FileOutputStream(file)
        out.flush()
        out.close()*/

        /*logoRef.getFile(localFile).addOnSuccessListener {
            Glide.with(this)
                .load(localFile)
                .into(iv_logo_tes)
        }.addOnFailureListener{

        }*/
    }

    private fun setupViews() {
        btn_tes.setOnClickListener {
            startActivity(Intent(activity, TesWarningActivity::class.java))
        }
    }
}