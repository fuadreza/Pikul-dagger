package io.github.fuadreza.pikul_dagger.views.tes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import io.github.fuadreza.pikul_dagger.data.remote.UnivFirestore
import io.github.fuadreza.pikul_dagger.model.SoalTes
import io.github.fuadreza.pikul_dagger.views.tes.model.Tes

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 09/07/2020.
 *
 */

class TesViewModel @ViewModelInject constructor(private val repo: UnivFirestore) : ViewModel(),
    LifecycleObserver {

    var allSoalTes: MutableLiveData<List<SoalTes>> = MutableLiveData()

    var soalState = MutableLiveData<TesViewState>()

    private val _tes = MutableLiveData<List<Tes>>()
    val tes: LiveData<List<Tes>> = _tes

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {
        fetchTes()
    }

    private fun fetchTes() {
        val listTes = arrayListOf(
            Tes(0, "1", "R"),
            Tes(1, "2", "I"),
            Tes(2, "3", "A"),
            Tes(3, "4", "S"),
            Tes(4, "5", "E"),
            Tes(5, "6", "C")
        )
        _tes.value = listTes
    }

}

sealed class TesViewState {
    object LoadUnivSuccess : TesViewState()

    data class ShowToast(var message: String) : TesViewState()
    data class OnLoadUnivState(val soalList: List<SoalTes>) : TesViewState()
}