package io.github.fuadreza.pikul_dagger.views.main.profile

import io.github.fuadreza.pikul_dagger.model.JawabanUser
import io.github.fuadreza.pikul_dagger.model.UserProfile

sealed class ProfileState {
    object LoadProfileSuccess : ProfileState()
    object LoadProfileError : ProfileState()
    object LoadProgressError : ProfileState()

    object LoadingState: ProfileState()
    data class ProfileLoaded(var data: UserProfile) : ProfileState()
    data class ProgressLoaded(var data: JawabanUser) : ProfileState()
}