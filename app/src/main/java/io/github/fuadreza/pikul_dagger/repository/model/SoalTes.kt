package io.github.fuadreza.pikul_dagger.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */
@Parcelize
class SoalTes (
    var kategori: String = "",
    var soal: String = "",
    var rasio: String = ""
): Parcelable