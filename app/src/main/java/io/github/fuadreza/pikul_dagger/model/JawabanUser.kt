package io.github.fuadreza.pikul_dagger.model

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

data class JawabanUser(
    val uid: String = "",
    val name: String = "",
    val skor_kat: List<String> = emptyList()
)