package io.github.fuadreza.pikul_dagger.di

import dagger.Module
import io.github.fuadreza.pikul_dagger.repository.user.UserComponent
import io.github.fuadreza.pikul_dagger.ui.login.LoginComponent
import io.github.fuadreza.pikul_dagger.ui.register.RegisterComponent

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */
@Module(subcomponents = [LoginComponent::class, RegisterComponent::class, UserComponent::class])
class AppSubcomponents