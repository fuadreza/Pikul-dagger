package io.github.fuadreza.pikul_dagger.ui.main.univ

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import io.github.fuadreza.pikul_dagger.repository.api.FirestoreService
import io.github.fuadreza.pikul_dagger.repository.model.Universitas
import javax.inject.Inject


/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */

class UnivViewModel @Inject constructor(private val repo: FirestoreService) : ViewModel() {

    var allUniversitas: MutableLiveData<List<Universitas>> = MutableLiveData()

    val univState = MutableLiveData<UnivViewState>()

    fun getAllUniversitas(){

        //Without limit
        /*repo.getAllUniversitas().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                allUniversitas.value = null
                return@EventListener
            }

            val allUniversitasList : MutableList<Universitas> = mutableListOf()
            for (doc in value!!) {
                val universitasItem = doc.toObject(Universitas::class.java)
                allUniversitasList.add(universitasItem)
            }
            allUniversitas.value = allUniversitasList
            univState.value = UnivViewState.OnLoadUnivState(allUniversitasList)
        })*/

        //With limit
        /*repo.getAllUniversitas().addOnSuccessListener { documentSnapshots ->
            if(documentSnapshots != null){
                allUniversitas.value = null
                return@addOnSuccessListener
            }

            val allUniversitasList : MutableList<Universitas> = mutableListOf()
            for (doc in documentSnapshots.documents){
                val universitasItem = doc.toObject(Universitas::class.java)
                allUniversitasList.add(universitasItem!!)
            }
            allUniversitas.value = allUniversitasList
            univState.value = UnivViewState.OnLoadUnivState(allUniversitasList)
        }*/
        repo.getAllUniversitas().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                allUniversitas.value = null
                return@EventListener
            }

            val allUniversitasList : MutableList<Universitas> = mutableListOf()
            for (doc in value!!) {
                val universitasItem = doc.toObject(Universitas::class.java)
                allUniversitasList.add(universitasItem)
            }
            allUniversitas.value = allUniversitasList
            univState.value = UnivViewState.OnLoadUnivState(allUniversitasList)
        })

        //return allUniversitas
    }

}

sealed class UnivViewState {
    object LoadUnivSuccess : UnivViewState()

    data class ShowToast(var message: String) : UnivViewState()
    data class OnLoadUnivState(val univList: List<Universitas>) : UnivViewState()
}

