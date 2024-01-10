package ppatsrrif.one.waterstate.domain.usecase

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateUseCase {

    fun getFormatTimeForLocale(time: Date): String {
        val defaultLocale = Locale.getDefault()
        val formatter24 = SimpleDateFormat("HH:mm", defaultLocale)
        val formatter12 = SimpleDateFormat("h:mm a", defaultLocale)

        val time24 = formatter24.format(time)
        val time12 = formatter12.format(time)

        return if (time12.contains("AM") || time12.contains("PM")) time12 else time24
    }

    fun getCurrentDate(): Date {
        return Calendar.getInstance().time
    }

    fun getCurrentStartDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun getCurrentEndDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }


}