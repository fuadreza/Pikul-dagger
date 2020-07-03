package io.github.fuadreza.pikul_dagger.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

@Parcelize
class JawabanUser(
    val uid: String = "",
    val name: String = "",
    val skor_kat: List<String> = emptyList()
) : Parcelable