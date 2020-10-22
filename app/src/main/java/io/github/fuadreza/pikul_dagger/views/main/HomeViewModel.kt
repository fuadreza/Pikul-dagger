package io.github.fuadreza.pikul_dagger.views.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import io.github.fuadreza.pikul_dagger.repository.UserRepository
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 28/06/2020.
 *
 */

class HomeViewModel @ViewModelInject constructor(private val userRepository: UserRepository) :
    ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun checkLogin() {
        viewModelScope.launch {

        }
    }

}