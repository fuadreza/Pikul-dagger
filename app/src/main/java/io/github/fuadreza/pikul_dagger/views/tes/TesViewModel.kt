package io.github.fuadreza.pikul_dagger.views.tes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import io.github.fuadreza.pikul_dagger.data.remote.FirestoreService
import io.github.fuadreza.pikul_dagger.model.SoalTes
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 09/07/2020.
 *
 */

class TesViewModel @Inject constructor(private val repo: FirestoreService) : ViewModel() {

    var allSoalTes: MutableLiveData<List<SoalTes>> = MutableLiveData()

    var soalState = MutableLiveData<TesViewState>()

    fun getAllSoals() {
        repo.getAllSoal().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                allSoalTes.value = null
                return@EventListener
            }

            val allSoalList: MutableList<SoalTes> = mutableListOf()
            for (doc in value!!) {
                val soalItem = doc.toObject(SoalTes::class.java)
                allSoalList.add(soalItem)
            }
            allSoalTes.value = allSoalList
            soalState.value = TesViewState.OnLoadUnivState(allSoalList)
        })
    }

    fun getSoalByCategory(kategori: String) {
        repo.getAllSoal().whereEqualTo("kategori", kategori).addSnapshotListener(
            EventListener<QuerySnapshot> { value, e ->
                if (e != null) {
                    allSoalTes.value = null
                    return@EventListener
                }

                val soalByKategori: MutableList<SoalTes> = mutableListOf()
                for (doc in value!!) {
                    val soalItem = doc.toObject(SoalTes::class.java)
                    soalByKategori.add(soalItem)
                }
                allSoalTes.value = soalByKategori
                soalState.value = TesViewState.OnLoadUnivState(soalByKategori)
            })
    }
}

sealed class TesViewState {
    object LoadUnivSuccess : TesViewState()

    data class ShowToast(var message: String) : TesViewState()
    data class OnLoadUnivState(val soalList: List<SoalTes>) : TesViewState()
}