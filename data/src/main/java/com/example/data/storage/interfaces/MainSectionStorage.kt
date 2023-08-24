package com.example.data.storage.interfaces

import com.example.domain.models.base.MainSectionType

interface MainSectionStorage {
    fun getSelected(): MainSectionType
    fun saveSelected(mainSectionType: MainSectionType)
}