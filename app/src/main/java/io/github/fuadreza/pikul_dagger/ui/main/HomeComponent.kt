package io.github.fuadreza.pikul_dagger.ui.main

import dagger.Subcomponent
import io.github.fuadreza.pikul_dagger.di.ActivityScope
import io.github.fuadreza.pikul_dagger.ui.main.univ.UnivFragment

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 03/07/2020.
 *
 */

@ActivityScope
@Subcomponent
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(activity: HomeActivity)
    fun inject(fragment: UnivFragment)
}