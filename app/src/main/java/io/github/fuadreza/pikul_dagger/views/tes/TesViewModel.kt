package io.github.fuadreza.pikul_dagger.views.tes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import io.github.fuadreza.pikul_dagger.data.local.entity.UserProgress
import io.github.fuadreza.pikul_dagger.model.SoalTes
import io.github.fuadreza.pikul_dagger.repository.UserProgressRepository
import io.github.fuadreza.pikul_dagger.views.tes.model.Tes
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 09/07/2020.
 *
 */

class TesViewModel @ViewModelInject constructor(private val repo: UserProgressRepository) :
    ViewModel(),
    LifecycleObserver {

    var allSoalTes: MutableLiveData<List<SoalTes>> = MutableLiveData()

    private val _tes = MutableLiveData<List<Tes>>()
    val tes: LiveData<List<Tes>> = _tes

    var userProgress: LiveData<UserProgress> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {
        fetchTes()
        fetchUserProgress()
    }

    private fun fetchUserProgress() {
        viewModelScope.launch(IO) {
            userProgress = repo.userProgress
        }
    }

    private fun fetchTes() {
        val listTes = arrayListOf(
            Tes(0, "1", "R", arrayListOf("1", "2", "3")),
            Tes(1, "2", "I", arrayListOf("4", "5", "6")),
            Tes(2, "3", "A", arrayListOf("7", "8", "9")),
            Tes(3, "4", "S", arrayListOf("10", "11", "12")),
            Tes(4, "5", "E", arrayListOf("13", "14", "15")),
            Tes(5, "6", "C", arrayListOf("16", "17", "18"))
        )
        _tes.value = listTes
    }

}

sealed class TesViewState {
    object LoadUnivSuccess : TesViewState()

    data class ShowToast(var message: String) : TesViewState()
    data class OnLoadUnivState(val soalList: List<SoalTes>) : TesViewState()
}