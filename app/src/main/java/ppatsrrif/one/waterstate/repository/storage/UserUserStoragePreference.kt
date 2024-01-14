package ppatsrrif.one.waterstate.repository.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import ppatsrrif.one.waterstate.repository.database.DataBase.Companion.DATABASE_NAME
import javax.inject.Inject

class UserUserStoragePreference @Inject constructor(
    @ApplicationContext private val context: Context
) : UserStorage {

    private val preferenceUser by lazy {
        context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
    }

    private val defaultPreferencesSettings by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    companion object {

        const val PREFERENCE_NAME = "preferences-user"

        // load mode
        const val KEY_START = "starts-key"

        // user
        const val KEY_NAME_USER = "name-user"
        const val DEFAULT_NAME_USER = "User"

        const val KEY_WEIGHT_USER = "weight-user"
        const val DEFAULT_WEIGHT_USER = 75.0f

        const val KEY_GENDER_USER = "gender-user"
        const val DEFAULT_GENDER_USER = 1

        const val KEY_PHYSICAL_USER = "physical-user"
        const val DEFAULT_PHYSICAL_USER = 1.2f

        // keys of default preferences
        const val KEY_RECOMMENDATION = "recommendation-who"


    }

    // WHO RECOMMENDATION
    override fun getStatusRecommendation(): Boolean =
        defaultPreferencesSettings.getBoolean(KEY_RECOMMENDATION, true)

    override fun setStatusRecommendation(status: Boolean) =
        defaultPreferencesSettings.edit().putBoolean(KEY_RECOMMENDATION, status).apply()

    // LOAD MODE
    override fun setLoadModePref(loadMode: String) {
        preferenceUser.edit().putString(KEY_START, loadMode).apply()
    }

    override fun getLoadModePref(): String? {
        return preferenceUser.getString(KEY_START, null)
    }

    // USER PROFILE
    override fun setUserName(name: String) {
        preferenceUser.edit().putString(KEY_NAME_USER, name).apply()
    }

    override fun getUserName(): String {
        return preferenceUser.getString(KEY_NAME_USER, DEFAULT_NAME_USER)!!
    }

    override fun setUserWeight(weight: Float) {
        preferenceUser.edit().putFloat(KEY_WEIGHT_USER, weight).apply()
    }

    override fun getUserWeight(): Float {
        return preferenceUser.getFloat(KEY_WEIGHT_USER, DEFAULT_WEIGHT_USER)
    }

    override fun setUserGender(gender: Int) {
        preferenceUser.edit().putInt(KEY_GENDER_USER, gender).apply()
    }

    override fun getUserGender(): Int {
        return preferenceUser.getInt(KEY_GENDER_USER, DEFAULT_GENDER_USER)
    }

    override fun setUserPhysical(physical: Float) {
        preferenceUser.edit().putFloat(KEY_PHYSICAL_USER, physical).apply()
    }

    override fun getUserPhysical(): Float {
        return preferenceUser.getFloat(KEY_PHYSICAL_USER, DEFAULT_PHYSICAL_USER)
    }

    override fun clearDataUser() {
        preferenceUser.edit().clear().apply()
        context.deleteDatabase(DATABASE_NAME)
    }


}