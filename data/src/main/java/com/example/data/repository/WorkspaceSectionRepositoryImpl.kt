package com.example.data.repository

import com.example.data.mapper.WorkspaceSectionMapper
import com.example.data.storage.interfaces.WorkspaceSectionStorage
import com.example.domain.models.WorkspaceSection
import com.example.domain.models.WorkspaceSectionCategory
import com.example.domain.models.WorkspaceSectionType
import com.example.domain.repository.WorkspaceSectionRepository

class WorkspaceSectionRepositoryImpl(
    private val workspaceSectionStorage: WorkspaceSectionStorage
): WorkspaceSectionRepository {

    override fun getSelected(): WorkspaceSectionType {
        return workspaceSectionStorage.getSelected()
    }

    override fun getSectionList(workspaceSectionCategory: WorkspaceSectionCategory): List<WorkspaceSection> {
        val items = workspaceSectionStorage.getSectionList(workspaceSectionCategory)
        val result = ArrayList<WorkspaceSection>()

        for (item in items) {
            result.add(
                WorkspaceSectionMapper.toDomain(
                    item,
                    item.type == workspaceSectionStorage.getSelected()
                )
            )
        }
        return result.toList()
    }

    override fun saveSelected(workspaceSectionType: WorkspaceSectionType) {
        workspaceSectionStorage.saveSelected(workspaceSectionType)
    }

}