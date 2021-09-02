package ppatsrrif.one.waterstate

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPreferencesHelper(context: Context) {

    val preferenceUser: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_USER, MODE_PRIVATE)

    private val preferenceStart: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_START_MODE, MODE_PRIVATE)

    private val preferencesSettings: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)


    // User name
    fun getUserName(): String = preferenceUser.getString(KEY_NAME_USER, "none") ?: "none"
    fun setUserName(name: String) = preferenceUser.edit().putString(KEY_NAME_USER, name).apply()

    // User weight
    fun getUserWeight(): Float = preferenceUser.getFloat(KEY_WEIGHT_USER, 30.0f)
    fun setUserWeight(weight: Float) = preferenceUser.edit().putFloat(KEY_WEIGHT_USER, weight).apply()

    fun getStartMode(): Int = preferenceStart.getInt(KEY_START, 0)
    fun setStartMode(type: Int) = preferenceStart.edit().putInt(KEY_START, type).apply()

    // WHO Recommendation
    fun getStatusRecommendation(): Boolean = preferencesSettings.getBoolean(KEY_RECOMMENDATION, true)
    fun setStatusRecommendation(status: Boolean) = preferencesSettings.edit().putBoolean(
        KEY_RECOMMENDATION, status).apply()

   companion object {

       // label
        const val PREFERENCE_USER = "user_preferences"

       // key
        const val KEY_NAME_USER = "name_user"
        const val KEY_WEIGHT_USER = "weight_user"

       // label
        const val PREFERENCE_START_MODE = "checkToPass"

       // key
        const val KEY_START = "starts_key"

       // keys of default preferences
       const val KEY_THEME_STATUS = "set_theme_pref"
       const val KEY_RECOMMENDATION = "recommendation_who"


    }
}