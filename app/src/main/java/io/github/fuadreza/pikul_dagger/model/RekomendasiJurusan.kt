package io.github.fuadreza.pikul_dagger.model

import com.google.gson.annotations.SerializedName

data class RekomendasiJurusan(
    @SerializedName("id")
    var id_kategori: String? = "",
    @SerializedName("kode")
    var kode_rekomendasi: String? = "",
    @SerializedName("rekomendasi")
    var rekomendasi: ArrayList<String?> = arrayListOf()
)