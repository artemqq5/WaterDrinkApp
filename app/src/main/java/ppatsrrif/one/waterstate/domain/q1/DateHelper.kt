package ppatsrrif.one.waterstate.domain.q1

import java.text.SimpleDateFormat
import java.util.*


class DateHelper {

    fun getDay(): Int {

        val dfD = SimpleDateFormat("u", Locale.CANADA)
        return dfD.format(Date()).toInt()

    }

    fun getWeek(): Int {
        val dfW = SimpleDateFormat("ww", Locale.CANADA)
        return dfW.format(Date()).toInt()
    }

}