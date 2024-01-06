package ppatsrrif.one.waterstate.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.repository.SharedPreferencesHelper


class ViewModelUser(application: Application) : AndroidViewModel(application) {

    val sharedPreferencesHelper: SharedPreferencesHelper by lazy {
        SharedPreferencesHelper(getApplication())
    }

    val liveDataName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val liveDataWeight: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    fun getStringWeight() =
        getApplication<Application>().resources.getString(R.string.unit_weight)

    init {
        liveDataName.value = sharedPreferencesHelper.getUserName()
        liveDataWeight.value = sharedPreferencesHelper.getUserWeight()
    }

}