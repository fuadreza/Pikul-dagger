package io.github.fuadreza.pikul_dagger.ui.register

import dagger.Subcomponent
import io.github.fuadreza.pikul_dagger.di.ActivityScope

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 29/06/2020.
 *
 */

@ActivityScope
@Subcomponent
interface RegisterComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegisterComponent
    }

    fun inject(activity: RegisterActivity)
}