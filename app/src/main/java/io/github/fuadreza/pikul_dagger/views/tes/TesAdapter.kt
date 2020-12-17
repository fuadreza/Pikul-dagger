package io.github.fuadreza.pikul_dagger.views.tes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.views.tes.detail_tes.DetailTesActivity
import io.github.fuadreza.pikul_dagger.views.tes.model.Tes

class TesAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<TesAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tes = emptyList<Tes>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val number: TextView = itemView.findViewById(R.id.tv_id_tes)
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_tes, parent, false)
        return ViewHolder(itemView).listen { pos, type ->
            //TODO Click listener ke detail tes
            val intent = Intent(parent.context, DetailTesActivity::class.java)
            val bundle = tes[pos]
            intent.putExtra(EXTRA_TES, bundle)
            parent.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = tes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.number.text = tes[position].name
    }

    internal fun setTes(tes: List<Tes>) {
        this.tes = tes
        notifyDataSetChanged()
    }

    companion object {
        const val EXTRA_TES = "TES"
    }
}