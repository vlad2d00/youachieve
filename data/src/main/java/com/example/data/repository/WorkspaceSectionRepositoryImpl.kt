package com.example.data.repository

import com.example.data.storage.interfaces.WorkspaceSectionStorage
import com.example.domain.models.workspace.WorkspaceSection
import com.example.domain.models.workspace.WorkspaceSectionCategory
import com.example.domain.models.workspace.WorkspaceSectionType
import com.example.domain.repository.WorkspaceSectionRepository

class WorkspaceSectionRepositoryImpl(
    private val workspaceSectionStorage: WorkspaceSectionStorage
): WorkspaceSectionRepository {

    override fun getSelected(): WorkspaceSectionType {
        return workspaceSectionStorage.getSelected()
    }

    override fun getDistance(
        sectionTypeFrom: WorkspaceSectionType,
        sectionTypeTo: WorkspaceSectionType,
        category: WorkspaceSectionCategory
    ): Int {
        return workspaceSectionStorage.getDistance(
            sectionTypeFrom = sectionTypeFrom,
            sectionTypeTo = sectionTypeTo,
            category = category
        )
    }

    override fun getSectionList(
        workspaceSectionCategory: WorkspaceSectionCategory
    ): List<WorkspaceSection> {
        return workspaceSectionStorage.getSectionList(workspaceSectionCategory)
    }

    override fun saveSelected(workspaceSectionType: WorkspaceSectionType) {
        workspaceSectionStorage.saveSelected(workspaceSectionType)
    }

}