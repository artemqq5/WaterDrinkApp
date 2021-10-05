package ppatsrrif.one.waterstate.mainPart.helperClasses

import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem

class CompareDates(private val sharedPref: SharedPreferencesHelper, private val viewModelItem: ViewModelItem) {

    fun checkWeek(dayNow: String, weekNow: Int) {
        if (sharedPref.getDateWeek() != 0) {
            if (weekNow != sharedPref.getDateWeek()) {
                sharedPref.setDateWeek(weekNow)
                sharedPref.setDateDay(dayNow)

                // clear dt Week and Day
                clearDTWeek()
                clearDTDay()

            } else checkDay(dayNow)

        } else {
            sharedPref.setDateWeek(weekNow)
            sharedPref.setDateDay(dayNow)
        }
    }

    // clear dt if dayNow > dayLast; if dayLast is 0 (dayLast == dayNow)
    private fun checkDay(dayNow: String) {
        if (dayNow != sharedPref.getDateDay()) {
            sharedPref.setDateDay(dayNow)

            // clear dt Day
            clearDTDay()

        }
    }


    private fun clearDTDay() {

        viewModelItem.deleteDayT()
    }

    private fun clearDTWeek() {

        viewModelItem.deleteWeekT()

        clearDTGoals()
    }

    private fun clearDTGoals() {
        viewModelItem.deleteGoalsT()
    }

}