package io.github.fuadreza.pikul_dagger.repository

import io.github.fuadreza.pikul_dagger.data.remote.UnivFirestore
import io.github.fuadreza.pikul_dagger.model.Universitas
import kotlinx.coroutines.flow.Flow

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */

class UnivRepository constructor(private val firestore: UnivFirestore) {

    fun getUnivs(): Flow<List<Universitas>> {
        //TODO INI DIGANTI, LIHAT PUNYA MAS ILHAM HOME REPOSITORY
        return getUnivs()
        /*return service.getDataFromDatabase("universitas")
            .flatMap { Flowable.fromIterable(it.children) }
            */
    }

}