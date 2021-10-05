package ppatsrrif.one.waterstate.mainPart.helperClasses

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    fun getDay(): String {

        val dfD = SimpleDateFormat("EEEE")
        return dfD.format(Date())

    }

    fun getWeek(): Int {
        val dfW = SimpleDateFormat("ww")
        return dfW.format(Date()).toInt()
    }

}