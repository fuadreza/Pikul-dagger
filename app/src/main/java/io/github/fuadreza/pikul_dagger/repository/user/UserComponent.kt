package io.github.fuadreza.pikul_dagger.repository.user

import dagger.Subcomponent
import io.github.fuadreza.pikul_dagger.ui.login.LoginActivity
import io.github.fuadreza.pikul_dagger.ui.main.HomeActivity

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

@LoggedUserScope
@Subcomponent
interface UserComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): UserComponent
    }

    fun inject(activity: HomeActivity)

    fun inject(activity: LoginActivity)
}