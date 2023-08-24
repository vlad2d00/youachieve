package com.example.data.storage.interfaces

interface DisplayStorage {
    fun getWidth(): Int
    fun getHeight(): Int
    fun saveWidth(width: Int)
    fun saveHeight(height: Int)
}