package io.github.fuadreza.pikul_dagger.views.tes.hasil_tes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.github.fuadreza.pikul_dagger.repository.SoalRepository

class HasilTesViewModel @ViewModelInject constructor(private val soalRepository: SoalRepository): ViewModel(), LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init () {

    }

}