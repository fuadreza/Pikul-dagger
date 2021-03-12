package io.github.fuadreza.pikul_dagger.views.tes.hasil_tes

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.model.JawabanUser
import io.github.fuadreza.pikul_dagger.model.RekomendasiJurusan
import io.github.fuadreza.pikul_dagger.utils.getImagesByCategory
import io.github.fuadreza.pikul_dagger.utils.getKategoriCode
import io.github.fuadreza.pikul_dagger.views.main.HomeActivity
import kotlinx.android.synthetic.main.activity_hasil_tes.*
import java.io.IOException

//TODO Hasil Tes
// [v] Halaman Hasil Tes
// [v] Get User Score
// [v] Sort top 3 kategori
// [v] Get recommendation based on top 3
// [v] Display recommendation

@AndroidEntryPoint
class HasilTesActivity : AppCompatActivity() {

    private val viewModel: HasilTesViewModel by viewModels()

    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_tes)
        supportActionBar?.hide()

        lifecycle.addObserver(viewModel)

        btnHandler()
    }

    override fun onResume() {
        super.onResume()
        observeUserScore()
        observeRekomendasi()
    }

    private fun observeUserScore() {
        viewModel.userId.observe(this) {
            viewModel.fetchUserProgress(it)
            userId = it
        }
        viewModel.userProgress.observe(this) { progress ->
            setupScore(progress)
            val userKategoriCode = getKategoriCode(progress.skor_kat)
            viewModel.fetchUserKategori(userKategoriCode.toString())

            setupImagesByKategori(getImagesByCategory(userKategoriCode.toString()))
        }
    }

    private fun setupImagesByKategori(images: String?) {
        try {
            val inputStream = assets.open("images/${images.toString()}")
            val drawable = Drawable.createFromStream(inputStream, null)
//            Log.d("GAMBAR", "IMAGES: ${drawable}")

            Glide.with(this)
                .load(drawable)
                .into(iv_illustration)

            inputStream.close()
        } catch (e: IOException) {
//            Log.d("GAMBAR", "IMAGES: error")
            return
        }

    }

    private fun observeRekomendasi() {
        viewModel.userRekomendasi.observe(this) {
            setupRekomendasi(it)
        }
    }

    private fun setupRekomendasi(rekomendasi: ArrayList<RekomendasiJurusan>) {
        var rekomendasiJurusan = ""

        if (!rekomendasi.isNullOrEmpty()) {
            rekomendasi[0].rekomendasi.let {
                if (it[0].isNullOrEmpty())
                    rekomendasiJurusan += "Tidak ditemukan jurusan yang cocok"
                else
                    it.forEachIndexed { index, value ->
                        rekomendasiJurusan += if (index == 0) "$value"
                        else ", $value"
                    }
            }
        }

        tv_rekomendasi_jurusan.text = rekomendasiJurusan
    }

    private fun btnHandler() {
        btn_home.setOnClickListener {
            val intent =Intent(this, HomeActivity::class.java)
            intent.apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }

    private fun setupScore(score: JawabanUser) {
        score.skor_kat.forEachIndexed { index, it ->
            when (index) {
                0 -> tv_kategori_r.text = it.toString()
                1 -> tv_kategori_i.text = it.toString()
                2 -> tv_kategori_a.text = it.toString()
                3 -> tv_kategori_s.text = it.toString()
                4 -> tv_kategori_e.text = it.toString()
                5 -> tv_kategori_c.text = it.toString()
                else -> {
                }
            }
        }
    }
}