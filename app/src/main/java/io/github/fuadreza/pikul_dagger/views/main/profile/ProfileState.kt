package io.github.fuadreza.pikul_dagger.views.main.profile

sealed class ProfileState {
    object LoadProfileSuccess : ProfileState()
    object LoadingState: ProfileState()

}