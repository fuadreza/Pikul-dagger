package io.github.fuadreza.pikul_dagger.views.tes.detail_tes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import io.github.fuadreza.pikul_dagger.model.SoalTes
import io.github.fuadreza.pikul_dagger.repository.SoalRepository

class DetailTesViewModel @ViewModelInject constructor(private val repo: SoalRepository) :
    ViewModel(), LifecycleObserver {

    private var soalTes = MutableLiveData<SoalTes>()

    val soalState = MutableLiveData<DetailTesState>()

    private var _soalIndex = MutableLiveData<Int>()
    val soalIndex: LiveData<Int> = _soalIndex

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {
        _soalIndex.value = 0
    }

    fun getSoalById(soalId: String) {
        repo.getSoalById(soalId)
            .addSnapshotListener(EventListener<DocumentSnapshot> { value, error ->
                if (error != null) {
                    soalTes.value = null
                    return@EventListener
                }

                try {
                    val doc = value?.toObject<SoalTes>()
                    soalTes.value = doc
                    soalState.value = DetailTesState.OnLoadSoalState(doc)
                } catch (e: FirebaseFirestoreException) {

                }

                /*val allSoalList = mutableListOf<SoalTes>()
                value.forEach { doc ->
                    val soal = doc.toObject(SoalTes::class.java)
                    allSoalList.add(soal)
                }*/
                /*soalTes.value = allSoalList
                soalState.value = DetailTesState.OnLoadSoalState(allSoalList)*/

            })
    }

    fun nextSoal(){
        val newIndex = _soalIndex.value?.plus(1)
        _soalIndex.value = newIndex
    }

}

sealed class DetailTesState {
    object LoadingState : DetailTesState()

    data class OnLoadSoalState(val soalList: SoalTes?) : DetailTesState()
}