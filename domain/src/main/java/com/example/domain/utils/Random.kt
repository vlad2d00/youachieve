package com.example.domain.utils

class Random {

    companion object {

        private val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')


        fun getRandomString(length: Int) : String {
            return (1..length)
                .map { chars.random() }
                .joinToString("")
        }

        fun getRandomString(lengthMin: Int, lengthMax: Int) : String {
            return (1..getRandomInt(lengthMin, lengthMax))
                .map { chars.random() }
                .joinToString("")
        }

        fun getRandomBoolean() : Boolean {
            return (0..1).random() == 1
        }

        fun getRandomInt(valueMin: Int, valueMax: Int) : Int {
            return (valueMin..valueMax).random()
        }

        fun getRandomLong(valueMin: Long, valueMax: Long) : Long {
            return (valueMin..valueMax).random()
        }

    }
}