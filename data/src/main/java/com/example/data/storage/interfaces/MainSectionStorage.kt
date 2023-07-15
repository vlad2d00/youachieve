package com.example.data.storage.interfaces

import com.example.domain.models.MainSectionType

interface MainSectionStorage {
    fun getSelected(): MainSectionType
    fun saveSelected(mainSectionType: MainSectionType)
}