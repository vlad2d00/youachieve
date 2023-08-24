package com.example.domain.utils

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern

class DatetimeService {

    companion object {

        private val formatFull = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        private val formatDateFull = DateTimeFormatter.ofPattern("dd.MM.yyyy")


        fun toStringFull(datetime: LocalDateTime): String {
            return datetime.year.toString().padStart(4, '0') + "-" +
                    datetime.month.toString().padStart(2, '0') + "-" +
                    datetime.dayOfMonth.toString().padStart(2, '0') + " " +
                    datetime.hour.toString().padStart(2, '0') + ":" +
                    datetime.minute.toString().padStart(2, '0') + ":" +
                    datetime.second.toString().padStart(2, '0') + "." +
                    (datetime.nano * 1e6 % 1000).toString().padStart(3, '0')
        }

        fun toStringFull(date: LocalDate): String {
            return date.dayOfMonth.toString().padStart(2, '0') + "." +
                    date.month.toString().padStart(2, '0') + "." +
                    date.year.toString().padStart(2, '0')
        }

        fun toStringShort(date: LocalDate): String {
            return date.dayOfMonth.toString().padStart(2, '0') + "." +
                    date.month.toString().padStart(2, '0') + "." +
                    (date.year % 100).toString().padStart(2, '0')
        }

        fun toStringShort(time: LocalTime): String {
            return time.hour.toString().padStart(2, '0') + ":" +
                    time.minute.toString().padStart(2, '0')
        }

        fun toDate(dateString: String): LocalDate {
            return LocalDateTime.parse(dateString, formatDateFull).toLocalDate()
        }

        fun toDatetime(datetimeString: String): LocalDateTime {
            return LocalDateTime.parse(datetimeString, formatFull)
        }

        fun monthsExcludeDays(dateBegin: LocalDate, dateEnd: LocalDate): Int {
            val dateBeginCopy = LocalDate.of(dateBegin.year, dateBegin.month, 0)
            val dateEndCopy = LocalDate.of(dateEnd.year, dateEnd.month, 0)
            return Period.between(dateBeginCopy, dateEndCopy).months
        }

    }
}