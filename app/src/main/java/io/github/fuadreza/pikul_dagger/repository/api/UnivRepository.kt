package io.github.fuadreza.pikul_dagger.repository.api

import io.github.fuadreza.pikul_dagger.repository.model.Universitas
import io.reactivex.Flowable

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */

class UnivRepository constructor(private val service: FirestoreService) {

    fun getUnivs(): Flowable<List<Universitas>> {
        //TODO INI DIGANTI, LIHAT PUNYA MAS ILHAM HOME REPOSITORY
        return getUnivs()
        /*return service.getDataFromDatabase("universitas")
            .flatMap { Flowable.fromIterable(it.children) }
            */
    }

}