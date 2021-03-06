package io.github.fuadreza.pikul_dagger.views.tes.hasil_tes

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import io.github.fuadreza.pikul_dagger.model.JawabanUser
import io.github.fuadreza.pikul_dagger.model.RekomendasiJurusan
import io.github.fuadreza.pikul_dagger.repository.UserProgressRepository
import io.github.fuadreza.pikul_dagger.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HasilTesViewModel @ViewModelInject constructor(
    private val repo: UserProgressRepository,
    private val userRepository: UserRepository
) : ViewModel(), LifecycleObserver {

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _userProgress = MutableLiveData<JawabanUser>()
    var userProgress: LiveData<JawabanUser> = _userProgress

    private val _userRekomendasi = MutableLiveData<ArrayList<RekomendasiJurusan>>()
    val userRekomendasi: LiveData<ArrayList<RekomendasiJurusan>> = _userRekomendasi

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {
        fetchUser()
    }

    private fun fetchUser() {
        _userId.value = userRepository.user?.uid
    }

    fun fetchUserProgress(uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getUserProgress(uid)
                .addSnapshotListener(EventListener<DocumentSnapshot> { value, error ->
                    if (error != null){
                        return@EventListener
                    }
                    try {
                        _userProgress.value = value?.toObject<JawabanUser>()
                    }catch (e: FirebaseFirestoreException) {
                        e.printStackTrace()
                    }
                })
        }
    }

    fun fetchUserKategori(kategori: String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getRecommendationByKategori(kategori)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        try {
                            val doc = it.result?.toObjects<RekomendasiJurusan>()
                            _userRekomendasi.value = doc as ArrayList<RekomendasiJurusan>?
                        }catch (e: FirebaseFirestoreException){
                            e.printStackTrace()
                        }
                    }else {
                        Log.d("firestore error", "error getting document: ${it.exception}")
                    }
                }
        }
    }
}