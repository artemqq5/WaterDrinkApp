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

    private val preferencesDBDates: SharedPreferences =
        context.getSharedPreferences(DB_DATE, MODE_PRIVATE)


    // User
    fun getUserName(): String = preferenceUser.getString(KEY_NAME_USER, "none") ?: "none"
    fun setUserName(name: String) = preferenceUser.edit().putString(KEY_NAME_USER, name).apply()

    fun getUserWeight(): Float = preferenceUser.getFloat(KEY_WEIGHT_USER, 30.0f)
    fun setUserWeight(weight: Float) = preferenceUser.edit().putFloat(KEY_WEIGHT_USER, weight).apply()

    // Start Mode
    fun getStartMode(): Int = preferenceStart.getInt(KEY_START, 0)
    fun setStartMode(type: Int) = preferenceStart.edit().putInt(KEY_START, type).apply()

    // WHO Recommendation
    fun getStatusRecommendation(): Boolean = preferencesSettings.getBoolean(KEY_RECOMMENDATION, true)
    fun setStatusRecommendation(status: Boolean) = preferencesSettings.edit().putBoolean(
        KEY_RECOMMENDATION, status).apply()

    // DB Date
    fun getDateDay(): String = preferencesDBDates.getString(KEY_DAY_L, "none") ?: "none"
    fun getDateWeek(): Int = preferencesDBDates.getInt(KEY_WEEK_L, 0)

    fun setDateDay(dayNum: String) = preferencesDBDates.edit().putString(KEY_DAY_L, dayNum).apply()
    fun setDateWeek(weekNum: Int) = preferencesDBDates.edit().putInt(KEY_WEEK_L, weekNum).apply()

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

       // label
       const val DB_DATE = "db_date1"
       // key
       const val KEY_DAY_L = "day_last1"
       const val KEY_WEEK_L = "week_last1"

       // keys of default preferences
       const val KEY_THEME_STATUS = "set_theme_pref"
       const val KEY_RECOMMENDATION = "recommendation_who"


    }
}