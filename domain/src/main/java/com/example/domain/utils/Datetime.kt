package com.example.domain.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Datetime {

    companion object {

        private val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")


        fun toString(datetime: LocalDateTime): String {
            return datetime.year.toString().padStart(4, '0') + "-" +
                    datetime.month.toString().padStart(2, '0') + "-" +
                    datetime.dayOfMonth.toString().padStart(2, '0') + " " +
                    datetime.hour.toString().padStart(2, '0') + ":" +
                    datetime.minute.toString().padStart(2, '0') + ":" +
                    datetime.second.toString().padStart(2, '0') + "." +
                    (datetime.nano * 1e6 % 1000).toString().padStart(3, '0')
        }

        fun toDatetime(datetimeString: String): LocalDateTime {
            return LocalDateTime.parse(datetimeString , format)
        }

    }
}