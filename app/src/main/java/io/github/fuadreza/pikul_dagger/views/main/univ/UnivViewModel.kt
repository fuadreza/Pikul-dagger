package io.github.fuadreza.pikul_dagger.views.main.univ

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import io.github.fuadreza.pikul_dagger.data.remote.FirestoreService
import io.github.fuadreza.pikul_dagger.model.Universitas
import java.io.File
import javax.inject.Inject


/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 02/07/2020.
 *
 */

class UnivViewModel @Inject constructor(private val repo: FirestoreService) : ViewModel() {

    var allUniversitas: MutableLiveData<List<Universitas>> = MutableLiveData()

    val univState = MutableLiveData<UnivViewState>()

    val storage = Firebase.storage

    val storageRef = storage.reference

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
            //download logo
            downloadLogo(allUniversitasList, context)

            allUniversitas.value = allUniversitasList
            univState.value = UnivViewState.OnLoadUnivState(allUniversitasList)
        })

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

