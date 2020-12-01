package io.github.fuadreza.pikul_dagger.views.tes.detail_tes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import io.github.fuadreza.pikul_dagger.model.SoalTes
import io.github.fuadreza.pikul_dagger.repository.SoalRepository

class DetailTesViewModel @ViewModelInject constructor(private val repo: SoalRepository) :
    ViewModel(), LifecycleObserver {

    private var soalTes = MutableLiveData<List<SoalTes>>()

    val soalState = MutableLiveData<DetailTesState>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {

    }

    fun getSoalsByCategory(kategori: String) {
        repo.getSoalByCategory(kategori)
            .addSnapshotListener(EventListener<QuerySnapshot> { value, error ->
                if (error != null) {
                    soalTes.value = null
                    return@EventListener
                }

                val allSoalList = mutableListOf<SoalTes>()
                for(doc in value!!){
                    val soal = doc.toObject(SoalTes::class.java)
                    allSoalList.add(soal)
                }
                soalTes.value = allSoalList
                soalState.value = DetailTesState.OnLoadSoalState(allSoalList)

            })
    }

}

sealed class DetailTesState {
    object LoadingState : DetailTesState()

    data class OnLoadSoalState(val soalList: List<SoalTes>) : DetailTesState()
}