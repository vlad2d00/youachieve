package com.example.data.storage.memory

import com.example.data.storage.interfaces.MainSectionStorage
import com.example.domain.models.base.MainSectionType


class MainSectionMemory: MainSectionStorage {

    companion object {
        private var sectionType_ = MainSectionType.WORKSPACE
    }

    override fun getSelected(): MainSectionType {
        return sectionType_
    }

    override fun saveSelected(mainSectionType: MainSectionType) {
        sectionType_ = mainSectionType
    }

}