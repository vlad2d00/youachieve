package com.example.data.storage.interfaces

import com.example.domain.models.workspace.WorkspaceSection
import com.example.domain.models.workspace.WorkspaceSectionCategory
import com.example.domain.models.workspace.WorkspaceSectionType

interface WorkspaceSectionStorage {
    fun getSelected(): WorkspaceSectionType
    fun getDistance(
        sectionTypeFrom: WorkspaceSectionType,
        sectionTypeTo: WorkspaceSectionType,
        category: WorkspaceSectionCategory
    ): Int

    fun getSectionList(workspaceSectionCategory: WorkspaceSectionCategory): List<WorkspaceSection>
    fun saveSelected(workspaceSectionType: WorkspaceSectionType)
}