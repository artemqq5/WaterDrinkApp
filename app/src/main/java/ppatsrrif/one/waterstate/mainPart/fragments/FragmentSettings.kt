package ppatsrrif.one.waterstate.mainPart.fragments

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