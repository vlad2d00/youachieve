package com.example.youachieve.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import com.example.domain.models.base.ResourceName
import com.example.domain.utils.DatetimeService
import com.example.youachieve.R
import java.time.*
import kotlin.math.abs


class MyResources {

    companion object {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun getDrawable(resourceName: ResourceName, context: Context): Drawable? {
            return when (resourceName) {
                ResourceName.WORKSPACE_SECTION_PROJECTS -> context.getDrawable(R.drawable.project)
                ResourceName.WORKSPACE_SECTION_TASKS -> context.getDrawable(R.drawable.task)
                ResourceName.WORKSPACE_SECTION_USERS -> context.getDrawable(R.drawable.users)
                ResourceName.WORKSPACE_SECTION_ACTIONS -> context.getDrawable(R.drawable.clock)
            }
        }

        fun getString(resourceName: ResourceName, context: Context): String {
            return when (resourceName) {
                ResourceName.WORKSPACE_SECTION_PROJECTS -> context.getString(R.string.workspace_section_projects)
                ResourceName.WORKSPACE_SECTION_TASKS -> context.getString(R.string.workspace_section_tasks)
                ResourceName.WORKSPACE_SECTION_USERS -> context.getString(R.string.workspace_section_users)
                ResourceName.WORKSPACE_SECTION_ACTIONS -> context.getString(R.string.workspace_section_actions)
            }
        }

        fun toString(month: Month, context: Context): String {
            return when (month) {
                Month.JANUARY -> context.getString(R.string.january)
                Month.FEBRUARY -> context.getString(R.string.february)
                Month.MARCH -> context.getString(R.string.march)
                Month.APRIL -> context.getString(R.string.april)
                Month.MAY -> context.getString(R.string.may)
                Month.JUNE -> context.getString(R.string.june)
                Month.JULY -> context.getString(R.string.july)
                Month.AUGUST -> context.getString(R.string.august)
                Month.SEPTEMBER -> context.getString(R.string.september)
                Month.OCTOBER -> context.getString(R.string.october)
                Month.NOVEMBER -> context.getString(R.string.november)
                Month.DECEMBER -> context.getString(R.string.december)
            }
        }

        fun toString(dayOfWeek: DayOfWeek, context: Context): String {
            return when (dayOfWeek) {
                DayOfWeek.MONDAY -> context.getString(R.string.monday)
                DayOfWeek.TUESDAY -> context.getString(R.string.tuesday)
                DayOfWeek.WEDNESDAY -> context.getString(R.string.wednesday)
                DayOfWeek.THURSDAY -> context.getString(R.string.thursday)
                DayOfWeek.FRIDAY -> context.getString(R.string.february)
                DayOfWeek.SATURDAY -> context.getString(R.string.saturday)
                DayOfWeek.SUNDAY -> context.getString(R.string.sunday)
            }
        }

        fun toStringTimePeriod(timeBegin: LocalTime, timeEnd: LocalTime, context: Context): String {
            return DatetimeService.toStringShort(time = timeBegin) + " – " +
                    DatetimeService.toStringShort(time = timeEnd)
        }

        fun toStringFull(date: LocalDate, context: Context): String {
            val dateNow = LocalDate.now()
            val days = Period.between(date, dateNow).days

            return if (date.year != dateNow.year) {
                DatetimeService.toStringShort(date = date)
            }
            else {
                when (days) {
                    1 -> context.getString(R.string.yesterday)
                    0 -> context.getString(R.string.today)
                    -1 -> context.getString(R.string.tomorrow)
                    else -> date.dayOfMonth.toString() + " " +
                            toString(month = date.month, context = context) + "."
                }
            }
        }

        fun toStringFull(datetime: LocalDateTime, context: Context): String {
            val dateNow = LocalDate.now()
            val date = datetime.toLocalDate()
            val time = datetime.toLocalTime()
            val days = abs(Period.between(date, dateNow).days)

            return if (date.year != dateNow.year) {
                DatetimeService.toStringShort(date = date) +
                        context.getString(R.string.word_in) + " " +
                        DatetimeService.toStringShort(time = time)
            }
            else {
                when (days) {
                    1 -> context.getString(R.string.yesterday) +
                            context.getString(R.string.word_in) + " " +
                            DatetimeService.toStringShort(time = time)

                    0 -> DatetimeService.toStringShort(time = time)

                    -1 -> context.getString(R.string.tomorrow) +
                            context.getString(R.string.word_in) + " " +
                            DatetimeService.toStringShort(time = time)

                    else -> datetime.dayOfMonth.toString() + " " +
                            toString(month = date.month, context = context) + "." +
                            context.getString(R.string.word_in) + " " +
                            DatetimeService.toStringShort(time = time)
                }
            }
        }

        fun toStringFull(datetimeBegin: LocalDateTime, datetimeEnd: LocalDateTime, context: Context): String {
            val dateNow = LocalDate.now()
            val dateBegin = datetimeBegin.toLocalDate()
            val dateEnd = datetimeEnd.toLocalDate()

            val timeBegin = datetimeBegin.toLocalTime()
            val timeEnd = datetimeEnd.toLocalTime()

            val daysToBegin = abs(Period.between(dateBegin, dateNow).days)
            val daysToEnd = abs(Period.between(dateEnd, dateNow).days)

            // Границы периода в разных годах
            return if (dateBegin.year != dateEnd.year) {
                toStringFull(date = dateBegin, context = context) + " – " +
                        toStringFull(date = dateBegin, context = context)
            }

            // Границы периода в одном месяце
            else if (dateBegin.month == dateEnd.month) {

                // Границы периода в одном дне
                if (dateBegin.dayOfMonth == dateEnd.dayOfMonth) {

                    // Год даты отличный от текущего года
                    return if (dateEnd.year != dateNow.year) {
                        DatetimeService.toStringShort(date = dateEnd) + " " +
                                context.getString(R.string.word_in) + " " +
                                toStringTimePeriod(timeBegin, timeEnd, context)
                    }
                    // День даты на расстоянии в 1 день от текущего
                    else if (daysToEnd == 1) {
                        if (dateEnd < dateNow) context.getString(R.string.yesterday) else
                            context.getString(R.string.tomorrow) + " " +
                                    context.getString(R.string.word_in) + " " +
                                    toStringTimePeriod(timeBegin, timeEnd, context)
                    }
                    // Текущий день
                    else if (daysToEnd == 0) {
                        toStringTimePeriod(timeBegin, timeEnd, context)
                    }
                    // В текущем году
                    else {
                        dateEnd.dayOfMonth.toString() + " " +
                                toString(month = dateEnd.month, context = context) + "."
                                context.getString(R.string.word_in) + " " +
                                        toStringTimePeriod(timeBegin, timeEnd, context)
                    }
                }

                // Границы периода в разных днях одного месяца
                else {

                    // В текущем году
                    if (dateEnd.year == dateNow.year) {
                        val dateBeginString = when(daysToBegin) {
                            1 -> context.getString(R.string.yesterday)
                            0 -> context.getString(R.string.today)
                            -1 -> context.getString(R.string.tomorrow)
                            else -> dateBegin.dayOfMonth.toString() + " " +
                                    toString(month = dateBegin.month, context = context) + "."
                        }
                        val dateEndString = when(daysToEnd) {
                            1 -> context.getString(R.string.yesterday)
                            0 -> context.getString(R.string.today)
                            -1 -> context.getString(R.string.tomorrow)
                            else -> dateEnd.dayOfMonth.toString() + " " +
                                    toString(month = dateEnd.month, context = context) + "."
                        }

                        // Формат: "число – дата"
                        if (abs(daysToBegin) >= 2 && abs(daysToEnd) >= 2) {
                            dateBegin.dayOfMonth.toString() + " – " +
                                    dateEnd.dayOfMonth.toString() + " " +
                                    toString(month = dateEnd.month, context = context) + "."
                        }
                        // Формат: "строка – строка"
                        else {
                            "$dateBeginString – $dateEndString"
                        }
                    }
                    // В году отличном от текущего
                    else {
                        toStringFull(date = dateBegin, context = context) + " – " +
                                toStringFull(date = dateBegin, context = context)
                    }
                }
            }

            // Границы периода в разных месяцах одного года
            else {

                // В текущем году
                if (dateEnd.year == dateNow.year) {
                    val dateBeginString = when(daysToBegin) {
                        1 -> context.getString(R.string.yesterday)
                        0 -> context.getString(R.string.today)
                        -1 -> context.getString(R.string.tomorrow)
                        else -> dateBegin.dayOfMonth.toString() + " " +
                                toString(month = dateBegin.month, context = context) + "."
                    }
                    val dateEndString = when(daysToEnd) {
                        1 -> context.getString(R.string.yesterday)
                        0 -> context.getString(R.string.today)
                        -1 -> context.getString(R.string.tomorrow)
                        else -> dateEnd.dayOfMonth.toString() + " " +
                                toString(month = dateEnd.month, context = context) + "."
                    }
                    "$dateBeginString – $dateEndString"
                }
                // В году отличном от текущего
                else {
                    toStringFull(date = dateBegin, context = context) + " – " +
                            toStringFull(date = dateBegin, context = context)
                }
            }
        }

    }
}