package io.github.fuadreza.pikul_dagger.model

import com.google.gson.annotations.SerializedName


/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */

data class SoalTes(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("kategori")
    var kategori: String? = "",
    @SerializedName("rasio")
    var rasio: String? = "",
    @SerializedName("soal")
    var soal: String? = ""
)