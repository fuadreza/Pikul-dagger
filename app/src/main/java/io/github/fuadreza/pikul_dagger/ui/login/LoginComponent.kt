package io.github.fuadreza.pikul_dagger.ui.login

import dagger.Subcomponent
import io.github.fuadreza.pikul_dagger.di.ActivityScope

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

@ActivityScope
@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(activity: LoginActivity)
}