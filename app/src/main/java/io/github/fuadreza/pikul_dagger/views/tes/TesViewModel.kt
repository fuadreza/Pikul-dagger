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
            Tes(0, "1", "R", arrayListOf("9OTVwAWtfUajofbXnoza", "AiSb4G5OLqt2xzt2xOBw", "B27LtydWIRw7GobZVdlD", "E2yebZpwGqDhGZoiB0IP", "EGqmshlb7FLWj4eE4lN3", "KjMUvQOoQelP3UiyqTmY", "LckuTXUT35k2aJflYEcZ", "QdlAIj46k7vG4x9uacOx", "RmVjhkMqqR8GAxhJuzXD", "WBPRmxQUfXJXaBoOe4i8", "X4MVQiGxDDrHV3fN8kGL", "fFpGbsDQ2lqDw4kkMjYo", "hf25ZHvYQRgjtFw5kREM", "lpZXVnWFPo4VRAaq2ovx", "lvlOXni58Mk0R9DZu9At")),
            Tes(1, "2", "I", arrayListOf("6cpKyZbMjqCetWg6dns3", "AeXLBhKyn35JejkVMcgk", "CsTZDFQR80h5jHA6Qq42", "DWMzZjbKtKkxrAgiDb2g", "EzlMQRQAH1iuVlbkW5W9", "GMaiGaIMsrt8CBrbmTYJ", "IvXBa1YItNf6H8iODnsC", "KOISaqnFm6VQiJWgtz9E", "QOhdrtubA85Tn4P36VnA", "TOlL8IdKBBKluXOUgLWQ", "UspHmSv2rSBRlDfGY7o2", "ezhhbpL1SkZWOcNq7X55", "ifP8J09eG6TKoNZ20igQ", "o35TSip5oKgepDYHUuEc", "owPHj2lONK05pq7TgUms")),
            Tes(2, "3", "A", arrayListOf("ClotIo5mAu5CGUJXIm7k", "EPlcyroU9Uf6xJTL48Vh", "GVO2gYDs7WRTxjSyiMLU", "HKunmVfhQ2jJojeKgcjc", "HQryyjntcizhoYOUmnUm", "Jz519IOcsTfwxBTtA0vr", "O3pkksPFQK1m0qPxzHNN", "Ol4flOTxa6KfPX82YEDi", "RWh736CpIdOWijSPlm0E", "S1XbpTilvB2WlsfJenuq", "S9tpLBP9ltkOumSv4nS1", "UoD7a4vjonoUWRhaRTQn", "jxhGoRRMu7gc5AMfpDon", "n0WMNaibzeI3ASz3M0nP", "tW9MumrYBVygurYJaglb")),
            Tes(3, "4", "S", arrayListOf("155QBHfyPoMgijnpd2Xq", "2MpHZC3f4fPEKLofArgY", "6vssvbnouQy4Gmqi5Czk", "9xm2dI3yhDGONUJBckiu", "AwSLyr4hzRSEZgoaoBmJ", "B855mEkoKPV5kETjGWD2", "aGmbIMSl3BiVNGBP993Z", "bus9qqHR3t2IsP7WJXRp", "f9ZkBDdOgrHxwXblHhnA", "gAZDe5RfFCW1MQkaARLp", "jGLc1y4zZdMnhKrTuifP", "jIMohE97fMBC7WXxZhYk", "jyE8Urs42HJCJdQ5mdRM", "q53YLXwEMGqukLdCQ4AC", "rk1jncR6aO86eaz4pV1k")),
            Tes(4, "5", "E", arrayListOf("4QIAxwrqqN1n2ohPu5p0", "8AiniRV7rH6lAEaR4v7t", "A1hte3I0JTBcFUGBT1Bb", "Cmc7iRDCSUHt0Af8nuKS", "FqCgnuHfkR30oeYGVrKZ", "L3YEJFSayp8rtbTyLWWh", "MbLBk4MhUmizJC7sbYQY", "QGSAlipiUmGZVWXr2rnw", "VFMQa0OykMSLQYBHZD4G", "fbp9lIJs34wEMOriLKCH", "gEkwScxzv8ngPDgIFvm3", "hs3qujKeKUml7HK2OPzn", "lAeziCKNACKkBx1HWjPC", "oyePOokdMv5hwRGXYCHo", "rRQ5XOeGVri9Je6lSy2C")),
            Tes(5, "6", "C", arrayListOf("27vbU1UxIAaI6RnBGNaO", "2DbYhiDWQcdNKJ8lWVau", "3YvsKVposwAeVWW4h2tK", "6nT405jqHDX5typF9OAY", "FEpoRVAFVn9hhFD1wUgA", "IzgRZ9qw5PoyrgFeNLjw", "SEYBp6e3AioKiAnOkKtk", "cvWnHqMdT094kLDvJwH0", "gEZhm7UqNC63nP83eHO6", "hSQGaWxiVJ6iuDdj9lbH", "j1WQP7tVff8DWFHAdpIL", "mSxPsSZQWb2e4fMy2ohL", "pGgKhEuMlRaPkbHiQsVD", "qM9xn5LXHtCJXgEf0Pr5", "uJq3w6mKtNAaf7xo2GxS"))
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