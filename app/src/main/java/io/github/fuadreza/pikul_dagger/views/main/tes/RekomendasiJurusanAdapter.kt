package io.github.fuadreza.pikul_dagger.views.main.tes


import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import io.github.fuadreza.pikul_dagger.R
import kotlinx.android.synthetic.main.item_rekomendasi.view.*

class RekomendasiJurusanAdapter(private val jurusan: String): Item() {

    override fun getLayout(): Int = R.layout.item_rekomendasi

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.apply {
            this.tv_nama_jurusan.text = jurusan
        }
    }
}