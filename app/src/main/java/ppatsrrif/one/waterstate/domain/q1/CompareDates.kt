package ppatsrrif.one.waterstate.domain.q1

import ppatsrrif.one.waterstate.repository.storage.UserUserStoragePreference
import ppatsrrif.one.waterstate.presentation.viewmodel.WaterViewModel

class CompareDates(
    private val sharedPref: UserUserStoragePreference,
    private val waterViewModel: WaterViewModel
) {

//    fun checkWeek(weekNow: Int) {
//        if (sharedPref.getDateWeek() != 0) {
//            if (weekNow != sharedPref.getDateWeek()) {
//                sharedPref.setDateWeek(weekNow)
//
//                // set new range date
////                setNewRange()
//
//                // clear dt Week and Day
//                clearDTStorage()
//            }
//
//        } else {
//            sharedPref.setDateWeek(weekNow)
////            setNewRange()
//        }
//    }

//    private fun clearDTStorage() {
//
//        viewModelItem.deleteTStorage()
//
//        clearDTGoals()
//    }
//
//    private fun clearDTGoals() {
//        viewModelItem.deleteGoalsT()
//    }
//
//    private fun setNewRange() {
//
//        val date = SimpleDateFormat("d", Locale.CANADA)
//
//        sharedPref.setStartDate(date.format(Date()).toInt())
//        sharedPref.setEndDate(date.format(Date()).toInt() + 6)
//    }
//
//    fun getStart(): Int {
//        return sharedPref.getStartDate()
//    }
//
//    fun getEnd(): Int {
//        return sharedPref.getEndDate()
//    }

}