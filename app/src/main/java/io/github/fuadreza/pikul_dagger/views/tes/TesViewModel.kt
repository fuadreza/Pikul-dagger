package io.github.fuadreza.pikul_dagger.views.tes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import io.github.fuadreza.pikul_dagger.model.JawabanUser
import io.github.fuadreza.pikul_dagger.model.SoalTes
import io.github.fuadreza.pikul_dagger.repository.UserProgressRepository
import io.github.fuadreza.pikul_dagger.repository.UserRepository
import io.github.fuadreza.pikul_dagger.views.tes.model.Tes
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 09/07/2020.
 *
 */

class TesViewModel @ViewModelInject constructor(private val repo: UserProgressRepository, private val userRepository: UserRepository) :
    ViewModel(),
    LifecycleObserver {

    val tesState = MutableLiveData<TesState>()

    var allSoalTes: MutableLiveData<List<SoalTes>> = MutableLiveData()

    private val _tes = MutableLiveData<List<Tes>>()
    val tes: LiveData<List<Tes>> = _tes

    private val _userProgress = MutableLiveData<JawabanUser>()
    var userProgress: LiveData<JawabanUser> = _userProgress

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {
        fetchTes()
        fetchUser()
    }

    private fun fetchUser(){
        _userId.value = userRepository.user?.uid
    }

    fun fetchUserProgress(uid: String) {
        viewModelScope.launch(IO) {
            repo.getUserProgress(uid)
                .addSnapshotListener(EventListener<DocumentSnapshot> {value, error ->
                    if (error != null){
                        return@EventListener
                    }
                    try {
                        _userProgress.value = value?.toObject<JawabanUser>()
                    }catch (e: FirebaseFirestoreException) {

                    }
                })
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

sealed class TesState {
    object LoadUnivSuccess : TesState()

    data class ShowToast(var message: String) : TesState()
    data class OnLoadUnivState(val soalList: List<SoalTes>) : TesState()
    data class OnLoadUserProgressState(val userProgress: JawabanUser) : TesState()
}