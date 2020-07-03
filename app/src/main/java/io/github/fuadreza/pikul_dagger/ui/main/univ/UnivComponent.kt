package io.github.fuadreza.pikul_dagger.ui.main.univ

import dagger.Subcomponent
import io.github.fuadreza.pikul_dagger.di.ActivityScope

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 03/07/2020.
 *
 */

@ActivityScope
@Subcomponent
interface UnivComponent  {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UnivComponent
    }

    fun inject(fragment: UnivFragment)

}