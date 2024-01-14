package ppatsrrif.one.waterstate.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ppatsrrif.one.waterstate.domain.repository.UserRepository
import ppatsrrif.one.waterstate.domain.repository.model.LoadMode
import ppatsrrif.one.waterstate.domain.repository.model.UserModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val mutableLiveDataUser: MutableLiveData<UserModel> = MutableLiveData()

    val liveDataUser: LiveData<UserModel>
        get() = mutableLiveDataUser

    fun setNewUser(userModel: UserModel) {
        userRepository.setUser(userModel)
        mutableLiveDataUser.postValue(userModel)
    }

    fun getDefaultUser(): UserModel {
        return userRepository.getUser()
    }

    fun setStatusRecommendation(status: Boolean) {
        userRepository.setStatusRecommendation(status)
    }

    fun getDefaultStatusRecommendation(): Boolean {
        return userRepository.getStatusRecommendation()
    }

    init {
        mutableLiveDataUser.postValue(getDefaultUser())
    }

    fun setLoadMode(loadMode: LoadMode) {
        userRepository.setLoadMode(loadMode)
    }

    fun getDefaultLoadMode(): LoadMode {
        return userRepository.getLoadMode()
    }

    fun clearDataUser() {
        userRepository.clearDataUser()
    }


}