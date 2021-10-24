package ppatsrrif.one.waterstate.mainPart.helperClasses

import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem
import java.text.SimpleDateFormat
import java.util.*

class CompareDates(
    private val sharedPref: SharedPreferencesHelper,
    private val viewModelItem: ViewModelItem
) {

    fun checkWeek(weekNow: Int) {
        if (sharedPref.getDateWeek() != 0) {
            if (weekNow != sharedPref.getDateWeek()) {
                sharedPref.setDateWeek(weekNow)

                // set new range date
//                setNewRange()

                // clear dt Week and Day
                clearDTStorage()
            }

        } else {
            sharedPref.setDateWeek(weekNow)
//            setNewRange()
        }
    }

    private fun clearDTStorage() {

        viewModelItem.deleteTStorage()

        clearDTGoals()
    }

    private fun clearDTGoals() {
        viewModelItem.deleteGoalsT()
    }

    private fun setNewRange() {

        val date = SimpleDateFormat("d", Locale.CANADA)

        sharedPref.setStartDate(date.format(Date()).toInt())
        sharedPref.setEndDate(date.format(Date()).toInt() + 6)
    }

    fun getStart(): Int {
        return sharedPref.getStartDate()
    }

    fun getEnd(): Int {
        return sharedPref.getEndDate()
    }

}