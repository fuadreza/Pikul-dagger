package io.github.fuadreza.pikul_dagger.di

import dagger.Module
import io.github.fuadreza.pikul_dagger.repository.user.UserComponent
import io.github.fuadreza.pikul_dagger.ui.login.LoginComponent
import io.github.fuadreza.pikul_dagger.ui.main.HomeComponent
import io.github.fuadreza.pikul_dagger.ui.register.RegisterComponent
import io.github.fuadreza.pikul_dagger.ui.setting.SettingComponent
import io.github.fuadreza.pikul_dagger.ui.tes.TesComponent

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */
@Module(subcomponents = [HomeComponent::class ,LoginComponent::class, RegisterComponent::class, SettingComponent::class, UserComponent::class, TesComponent::class])
class AppSubcomponents