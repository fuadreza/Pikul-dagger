package io.github.fuadreza.pikul_dagger.views.main.tes

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import io.github.fuadreza.pikul_dagger.model.JawabanUser
import io.github.fuadreza.pikul_dagger.model.RekomendasiJurusan
import io.github.fuadreza.pikul_dagger.repository.UserProgressRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeTesViewModel @ViewModelInject constructor(private val userProgressRepository: UserProgressRepository) :
    ViewModel(), LifecycleObserver {

    private val _userProgress = MutableLiveData<JawabanUser>()
    val userProgress: LiveData<JawabanUser> = _userProgress

    private val _userRekomendasiJurusan = MutableLiveData<ArrayList<RekomendasiJurusan>>()
    val userRekomendasiJurusan: LiveData<ArrayList<RekomendasiJurusan>> = _userRekomendasiJurusan

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchUserProgress(){
        viewModelScope.launch(IO) {
            userProgressRepository.getUserProgress()
                .addSnapshotListener { value, error ->
                    if(error != null){
                        return@addSnapshotListener
                    }

                    try {
                        _userProgress.value = value?.toObject<JawabanUser>()
                    }catch (e: FirebaseFirestoreException){

                    }
                }
        }
    }

    fun fetchRekomendasi(kategori: String){
        viewModelScope.launch(IO){
            userProgressRepository.getRecommendationByKategori(kategori)
                .addOnCompleteListener {
                    if( it.isSuccessful){
                        try {
                            val doc = it.result?.toObjects<RekomendasiJurusan>()
                            _userRekomendasiJurusan.value = doc as ArrayList<RekomendasiJurusan>?
                        }catch (e: FirebaseFirestoreException){
                            e.printStackTrace()
                        }
                    } else {
                        Log.d("firestore error", "error getting data for rekomendasi user")
                    }
                }
        }
    }
}