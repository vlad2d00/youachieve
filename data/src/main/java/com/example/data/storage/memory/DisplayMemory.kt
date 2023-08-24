package com.example.data.storage.memory

import com.example.data.storage.interfaces.DisplayStorage

class DisplayMemory: DisplayStorage {

    companion object {
        private var width_: Int = 0
        private var height_: Int = 0
    }

    override fun getWidth(): Int {
        return width_
    }

    override fun getHeight(): Int {
        return height_
    }

    override fun saveWidth(width: Int) {
        width_ = width
    }

    override fun saveHeight(height: Int) {
        height_ = height
    }

}