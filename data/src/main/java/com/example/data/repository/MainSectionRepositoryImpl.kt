package com.example.data.repository

import com.example.data.storage.interfaces.MainSectionStorage
import com.example.domain.models.base.MainSectionType
import com.example.domain.repository.MainSectionRepository

class MainSectionRepositoryImpl(
    private val mainSectionStorage: MainSectionStorage
): MainSectionRepository {

    override fun getSelected(): MainSectionType {
        return mainSectionStorage.getSelected()
    }

    override fun saveSelected(mainSectionType: MainSectionType) {
        mainSectionStorage.saveSelected(mainSectionType)
    }
}