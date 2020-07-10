package io.github.fuadreza.pikul_dagger.ui.tes

import dagger.Subcomponent
import io.github.fuadreza.pikul_dagger.di.ActivityScope
import io.github.fuadreza.pikul_dagger.ui.tes.detailtes.DetailTesActivity

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 09/07/2020.
 *
 */

@ActivityScope
@Subcomponent
interface TesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TesComponent
    }

    fun inject(activity: TesActivity)

    fun inject(activity: DetailTesActivity)
}