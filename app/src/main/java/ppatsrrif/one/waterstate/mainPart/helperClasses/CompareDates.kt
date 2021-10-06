package ppatsrrif.one.waterstate.mainPart.helperClasses

import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem

class CompareDates(private val sharedPref: SharedPreferencesHelper, private val viewModelItem: ViewModelItem) {

    fun checkWeek(weekNow: Int) {
        if (sharedPref.getDateWeek() != 0) {
            if (weekNow != sharedPref.getDateWeek()) {
                sharedPref.setDateWeek(weekNow)

                // clear dt Week and Day
                clearDTStorage()
            }

        } else {
            sharedPref.setDateWeek(weekNow)
        }
    }

    private fun clearDTStorage() {

        viewModelItem.deleteTStorage()

        clearDTGoals()
    }

    private fun clearDTGoals() {
        viewModelItem.deleteGoalsT()
    }

}