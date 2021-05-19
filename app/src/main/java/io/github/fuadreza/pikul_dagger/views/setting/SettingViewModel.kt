package io.github.fuadreza.pikul_dagger.views.setting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.github.fuadreza.pikul_dagger.repository.UserRepository

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 30/06/2020.
 *
 */

class SettingViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel(), LifecycleObserver{

    fun logout() {
        userRepository.logoutUser()
    }

}

sealed class SettingViewState() {

    object LogoutSuccess : SettingViewState()

}