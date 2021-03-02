package io.github.fuadreza.pikul_dagger.views.main.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import io.github.fuadreza.pikul_dagger.model.JawabanUser
import io.github.fuadreza.pikul_dagger.model.UserProfile
import io.github.fuadreza.pikul_dagger.repository.UserProgressRepository
import io.github.fuadreza.pikul_dagger.repository.UserRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ProfileViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val progressRepository: UserProgressRepository
) : ViewModel(), LifecycleObserver {

    val profileState = MutableLiveData<ProfileState>()

    private var _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchUser() {
        viewModelScope.launch(IO) {
            userRepository.getUserProfile()
                .addSnapshotListener(EventListener<DocumentSnapshot> { value, error ->
                    if (error != null) {
                        profileState.value = ProfileState.LoadProfileError
                        return@EventListener
                    }

                    try {
                        val doc = value?.toObject<UserProfile>()
                        profileState.value = ProfileState.ProfileLoaded(doc!!)
                    } catch (e: FirebaseFirestoreException) {
                        profileState.value = ProfileState.LoadProfileError
                        e.printStackTrace()
                    }
                })
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchUserProgress() {
        viewModelScope.launch(IO) {
            progressRepository.getUserProgress()
                .addSnapshotListener(EventListener<DocumentSnapshot> { value, error ->
                    if (error != null) {
                        profileState.value = ProfileState.LoadProgressError
                        return@EventListener
                    }

                    try {
                        val doc = value?.toObject<JawabanUser>()
                        if (doc != null) {
                            profileState.value = ProfileState.ProgressLoaded(doc)
                        } else {
                            profileState.value = ProfileState.LoadProgressError
                        }
                    } catch (e: FirebaseFirestoreException) {
                        profileState.value = ProfileState.LoadProgressError
                        e.printStackTrace()
                    }
                })
        }
    }
}