package com.example.domain.repository

import com.example.domain.models.MainSectionType

interface MainSectionRepository {
    fun getSelected(): MainSectionType
    fun saveSelected(mainSectionType: MainSectionType)
}