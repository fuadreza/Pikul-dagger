package io.github.fuadreza.pikul_dagger.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */
@Parcelize
data class Universitas (
    var nama_univ: String? = "",
    var akreditasi: String? = "",
    var logo_url: String? = ""
): Parcelable