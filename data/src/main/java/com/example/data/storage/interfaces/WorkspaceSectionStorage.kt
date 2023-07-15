package com.example.data.storage.interfaces

import com.example.data.models.WorkspaceSectionDto
import com.example.domain.models.WorkspaceSectionCategory
import com.example.domain.models.WorkspaceSectionType

interface WorkspaceSectionStorage {
    fun getSelected(): WorkspaceSectionType
    fun getSectionList(workspaceSectionCategory: WorkspaceSectionCategory): List<WorkspaceSectionDto>
    fun saveSelected(workspaceSectionType: WorkspaceSectionType)
}