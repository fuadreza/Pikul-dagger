package io.github.fuadreza.pikul_dagger.repository.model

import com.google.gson.annotations.SerializedName

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */

data class UniversitasModel(
    @field:SerializedName("nama_univ")
    private val nama_univ: String? = "",

    @field:SerializedName("akreditasi")
    private val akreditasi: String? = "",

    @field:SerializedName("logo_url")
    private val logo_url: String? = ""
) {

    fun transform(): Universitas = Universitas(
        nama_univ = this.nama_univ,
        akreditasi = this.akreditasi,
        logo_url = this.logo_url
    )
}