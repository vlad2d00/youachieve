package com.example.domain.repository

interface DisplayRepository {
    fun getWidth(): Int
    fun getHeight(): Int
    fun saveWidth(width: Int)
    fun saveHeight(height: Int)
}