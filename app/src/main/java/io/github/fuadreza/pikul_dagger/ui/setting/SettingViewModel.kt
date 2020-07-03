package io.github.fuadreza.pikul_dagger.ui.setting

import io.github.fuadreza.pikul_dagger.repository.user.UserManager
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 30/06/2020.
 *
 */

class SettingViewModel @Inject constructor(private var userManager: UserManager){

    fun logout(){
        userManager.logoutUser()
    }

}

sealed class SettingViewState(){

    object LogoutSuccess: SettingViewState()

}