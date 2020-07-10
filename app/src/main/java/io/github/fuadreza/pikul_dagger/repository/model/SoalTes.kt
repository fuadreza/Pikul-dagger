package io.github.fuadreza.pikul_dagger.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */
@Parcelize
data class SoalTes (
    var id_soal : String? = "",
    var kategori: String? = "",
    var rasio: String? = "",
    var soal: String? = ""
): Parcelable