package ppatsrrif.one.waterstate.domain.usecase

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateUseCase {

    fun getCurrentDateTimeFormatted(date: Date): String {
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm EEEE", Locale.ENGLISH)
        return formatter.format(date)
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