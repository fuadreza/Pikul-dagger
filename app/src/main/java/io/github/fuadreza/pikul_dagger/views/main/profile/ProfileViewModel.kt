package io.github.fuadreza.pikul_dagger.views.main.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ProfileViewModel @ViewModelInject constructor(): ViewModel(), LifecycleObserver {

    val profileState = MutableLiveData<ProfileState>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchUser(){
        viewModelScope.launch(IO) {

        }
    }
}

sealed class ProfileState {
    object LoadProfileSuccess : ProfileState()
    object LoadingState: ProfileState()

}