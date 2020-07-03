package io.github.fuadreza.pikul_dagger.ui.setting

import dagger.Subcomponent

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 30/06/2020.
 *
 */

@Subcomponent
interface SettingComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): SettingComponent
    }

    fun inject(activity: SettingActivity)
}