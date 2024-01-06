package ppatsrrif.one.waterstate.presentation.home

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import ppatsrrif.one.waterstate.R

class FragmentSettings : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)
    }

    companion object {

        fun newInstance() = FragmentSettings()
    }


}