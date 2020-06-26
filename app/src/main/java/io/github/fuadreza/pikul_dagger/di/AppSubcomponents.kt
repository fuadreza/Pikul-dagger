package io.github.fuadreza.pikul_dagger.di

import dagger.Module
import io.github.fuadreza.pikul_dagger.repository.user.UserComponent
import io.github.fuadreza.pikul_dagger.ui.login.LoginComponent

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */
@Module(subcomponents = [LoginComponent::class, UserComponent::class])
class AppSubcomponents