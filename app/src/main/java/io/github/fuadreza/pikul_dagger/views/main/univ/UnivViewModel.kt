package io.github.fuadreza.pikul_dagger.views.main.univ

import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.fuadreza.pikul_dagger.data.remote.UnivFirestore
import io.github.fuadreza.pikul_dagger.model.Universitas
import io.github.fuadreza.pikul_dagger.repository.UnivRepository
import io.github.fuadreza.pikul_dagger.repository.UserRepository
import java.io.File


/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */

class UnivViewModel @ViewModelInject constructor(private val firestore: UnivFirestore, @ApplicationContext val context: Context) : ViewModel(), LifecycleObserver {

    var allUniversitas: MutableLiveData<List<Universitas>> = MutableLiveData()

    val univState = MutableLiveData<UnivViewState>()

    val storage = Firebase.storage

    val storageRef = storage.reference

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchUnivs(){
        firestore.getAllUniv()
            .addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
                if (e != null){
                    Log.w("UNIV_VIEW_MODEL", "Listen Failed", e)
                    return@EventListener
                }

                val savedUnivList : MutableList<Universitas> = mutableListOf()

                for(doc in value!!){
                    val univItem = doc.toObject(Universitas::class.java)
                    savedUnivList.add(univItem)
                }
                downloadLogo(savedUnivList, context)
                allUniversitas.value = savedUnivList

            })

        //download logo

    }

    fun getAllUniversitas(context: Context?){

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
            //download logo
            downloadLogo(allUniversitasList, context)

            allUniversitas.value = allUniversitasList
            univState.value = UnivViewState.OnLoadUnivState(allUniversitasList)
        })*/


        //return allUniversitas
    }

    fun downloadLogo(listUniv: MutableList<Universitas>, context: Context?){
        listUniv.forEach {
            val logoRef = storageRef.child("logo_univ/" + it.logo_url)

            //val localFile = File.createTempFile(it.logo_url.toString(), "png")

            val dataPath = File(context?.filesDir, "logo_image")
            if(!dataPath.exists()){
                dataPath.mkdir()
            }
            Log.d("Direktori data", "PATH : " + dataPath)

            val localFile = File(dataPath, it.logo_url.toString())

            Log.d("Direktori lokal", "PATH : " + localFile)

            /*logoRef.getFile(localFile).addOnSuccessListener {
                Log.d("firebase ",";local tem file created  created " +localFile.toString())
            }*/

            it.logo_uri = localFile.path
        }
    }

}

sealed class UnivViewState {
    object LoadUnivSuccess : UnivViewState()

    data class ShowToast(var message: String) : UnivViewState()
    data class OnLoadUnivState(val univList: List<Universitas>) : UnivViewState()
}

