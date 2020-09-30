package io.github.fuadreza.pikul_dagger.views.setting

import io.github.fuadreza.pikul_dagger.repository.UserRepository
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 30/06/2020.
 *
 */

class SettingViewModel constructor(private var userRepository: UserRepository){

    fun logout(){
        userRepository.logoutUser()
    }

}

sealed class SettingViewState(){

    object LogoutSuccess: SettingViewState()

}