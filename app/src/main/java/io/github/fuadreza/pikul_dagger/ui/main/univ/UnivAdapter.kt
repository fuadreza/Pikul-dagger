package io.github.fuadreza.pikul_dagger.ui.main.univ

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.repository.model.Universitas
import kotlinx.android.synthetic.main.item_univ.view.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */

class UnivAdapter (private val univ: Universitas): Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tv_univ.text = univ.nama_univ
            tv_akreditasi.text = univ.akreditasi
            tv_logo.text = univ.logo_url
        }
    }

    override fun getLayout(): Int = R.layout.item_univ
}