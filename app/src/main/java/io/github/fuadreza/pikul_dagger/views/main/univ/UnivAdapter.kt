package io.github.fuadreza.pikul_dagger.views.main.univ

import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.model.Universitas
import kotlinx.android.synthetic.main.item_univ.view.*
import java.io.File

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */

class UnivAdapter(private val univ: Universitas) : Item() {

    var univFilterList = ArrayList<Universitas>()

    val storage = Firebase.storage

    val storageRef = storage.reference

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tv_nama_univ.text = univ.nama_univ
            tv_akreditasi.text = univ.akreditasi

            val logoRef = storageRef.child("logo_univ/" + univ.logo_url)

            //val localFile = File.createTempFile(univ.logo_url.toString(), "png")

            val localFile = File(univ.logo_uri.toString())

            if (localFile.exists()) {
                Glide.with(this)
                    .load(localFile)
                    .into(iv_logo)
            } else {
                logoRef.getFile(localFile).addOnSuccessListener {
                    Glide.with(this)
                        .load(localFile)
                        .into(iv_logo)
                }.addOnFailureListener {

                }
            }
            //TODO kalau scroll cepat gambar masih berubah2
            // https://stackoverflow.com/questions/50092376/loading-images-from-firebase-storage-in-recycler-shows-images-changing
        }
    }

    override fun getLayout(): Int = R.layout.item_univ

}