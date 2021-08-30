package ppatsrrif.one.waterstate.mainPart.viewModel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ppatsrrif.one.waterstate.SharedPreferencesHelper


class ViewModelUser(application: Application) : AndroidViewModel(application),
    SharedPreferences.OnSharedPreferenceChangeListener {

    val sharedPreferencesHelper: SharedPreferencesHelper by lazy {
        SharedPreferencesHelper(getApplication())
    }

    val liveDataName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val liveDataWeight: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    init {
        liveDataName.value = sharedPreferencesHelper.getUserName()
        liveDataWeight.value = sharedPreferencesHelper.getUserWeight()

        sharedPreferencesHelper.preferenceUser.registerOnSharedPreferenceChangeListener(this)
    }


    // set data from dialogUserEdit to LiveData
    override fun onSharedPreferenceChanged(preferences: SharedPreferences?, key: String?) {
        when(key) {
            SharedPreferencesHelper.KEY_NAME_USER ->
                liveDataName.value = sharedPreferencesHelper.getUserName()

            SharedPreferencesHelper.KEY_WEIGHT_USER ->
                liveDataWeight.value = sharedPreferencesHelper.getUserWeight()
        }
    }

    override fun onCleared() {

        sharedPreferencesHelper.preferenceUser.unregisterOnSharedPreferenceChangeListener(this)

        super.onCleared()
    }

}