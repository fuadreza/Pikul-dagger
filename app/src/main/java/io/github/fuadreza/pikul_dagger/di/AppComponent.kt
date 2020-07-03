package io.github.fuadreza.pikul_dagger.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.github.fuadreza.pikul_dagger.repository.api.FirestoreService
import io.github.fuadreza.pikul_dagger.repository.user.UserManager
import io.github.fuadreza.pikul_dagger.ui.login.LoginComponent
import io.github.fuadreza.pikul_dagger.ui.main.HomeComponent
import io.github.fuadreza.pikul_dagger.ui.main.univ.UnivComponent
import io.github.fuadreza.pikul_dagger.ui.register.RegisterComponent
import io.github.fuadreza.pikul_dagger.ui.setting.SettingComponent
import javax.inject.Singleton

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

@Singleton
@Component(modules = [AppSubcomponents::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun loginComponent(): LoginComponent.Factory

    fun registerComponent(): RegisterComponent.Factory

    fun settingComponent(): SettingComponent.Factory

    fun homeComponent(): HomeComponent.Factory

    //fun univComponent(): UnivComponent.Factory

    fun userManager(): UserManager

}