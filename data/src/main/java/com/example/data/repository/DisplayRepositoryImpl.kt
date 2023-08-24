package com.example.data.repository

import com.example.data.storage.interfaces.DisplayStorage
import com.example.domain.repository.DisplayRepository

class DisplayRepositoryImpl(
    private val displayStorage: DisplayStorage
): DisplayRepository {

    override fun getWidth(): Int {
        return displayStorage.getWidth()
    }

    override fun getHeight(): Int {
        return displayStorage.getHeight()
    }

    override fun saveWidth(width: Int) {
        displayStorage.saveWidth(width)
    }

    override fun saveHeight(height: Int) {
        displayStorage.saveHeight(height)
    }

}