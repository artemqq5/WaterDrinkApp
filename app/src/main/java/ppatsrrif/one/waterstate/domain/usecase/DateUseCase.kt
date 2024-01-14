package ppatsrrif.one.waterstate.domain.usecase

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DateUseCase @Inject constructor() {

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

    fun getCurrentStartDate(daysAgo: Int = 0): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -daysAgo)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun getCurrentEndDate(daysAgo: Int = 0): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -daysAgo + 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun getCurrentWeekStartDate(): Date {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val daysToSubtract = if (dayOfWeek == Calendar.SUNDAY) 6 else dayOfWeek - Calendar.MONDAY
        calendar.add(Calendar.DAY_OF_MONTH, -daysToSubtract)

        return calendar.time
    }

    fun getCurrentWeekEndDate(): Date {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val daysToAdd = if (dayOfWeek == Calendar.SUNDAY) 0 else Calendar.SATURDAY - dayOfWeek + 1
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd)

        return calendar.time
    }

    fun toWeeklyDateFormat(date: Date): String {
        return SimpleDateFormat("MMM dd", Locale.getDefault()).format(date)
    }

    fun isDateInRange(date: Date, startDate: Date, endDate: Date): Boolean {
        return date.after(startDate) && date.before(endDate) || date == startDate || date == endDate
    }



}