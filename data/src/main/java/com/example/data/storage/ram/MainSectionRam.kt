package com.example.data.storage.ram

import com.example.data.storage.interfaces.MainSectionStorage
import com.example.domain.models.MainSectionType


class MainSectionRam(): MainSectionStorage {

    companion object {
        private var sectionTypeValue = MainSectionType.WORKSPACE
    }

    override fun getSelected(): MainSectionType {
        return sectionTypeValue
    }

    override fun saveSelected(mainSectionType: MainSectionType) {
        sectionTypeValue = mainSectionType
    }

}