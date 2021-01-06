package io.github.fuadreza.pikul_dagger.views.tes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.views.tes.detail_tes.DetailTesActivity
import io.github.fuadreza.pikul_dagger.views.tes.model.Tes

class TesAdapter internal constructor(private var context: Context) :
    RecyclerView.Adapter<TesAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tes = emptyList<Tes>()
    private var skorTes = arrayListOf<Int>()
    private var userId = ""

    private var userProgress = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val number: TextView = itemView.findViewById(R.id.tv_id_tes)
        val checked: ImageView = itemView.findViewById(R.id.iv_checked)
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
            if (pos == userProgress) {
                val intent = Intent(parent.context, DetailTesActivity::class.java)
                val bundle = tes[pos]
                intent.putExtra(EXTRA_TES, bundle)
                intent.putExtra(EXTRA_SKOR, skorTes)
                intent.putExtra(EXTRA_UID, userId)
                parent.context.startActivity(intent)
            } else if(pos > userProgress) {
                Toast.makeText(parent.context, "Selesaikan tes sebelumnya", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(parent.context, "Tes sudah dikerjakan", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun getItemCount(): Int = tes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.number.text = tes[position].name
        if (position <= userProgress) {
            holder.number.setTextColor(ContextCompat.getColor(context, R.color.primary))
        }
        if (position < userProgress) {
            holder.number.visibility = View.INVISIBLE
            holder.checked.visibility = View.VISIBLE
        }
    }

    internal fun setTes(tes: List<Tes>) {
        this.tes = tes
        notifyDataSetChanged()
    }

    internal fun setUserProgress(
        userProgress: Int,
        skorTes: ArrayList<Int>,
        userId: String?
    ) {
        this.userProgress = userProgress
        this.skorTes = skorTes
        this.userId = userId.toString()
        notifyDataSetChanged()
    }

    companion object {
        const val EXTRA_UID = "UID"
        const val EXTRA_TES = "TES"
        const val EXTRA_SKOR = "SKOR"
    }
}