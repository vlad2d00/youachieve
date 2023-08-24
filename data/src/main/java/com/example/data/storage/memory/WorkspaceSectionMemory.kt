package com.example.data.storage.memory

import com.example.data.storage.interfaces.WorkspaceSectionStorage
import com.example.data.storage.memory.mapper.WorkspaceSectionMapper
import com.example.data.storage.memory.models.WorkspaceSectionDto
import com.example.domain.models.base.ResourceName
import com.example.domain.models.workspace.WorkspaceSection
import com.example.domain.models.workspace.WorkspaceSectionCategory
import com.example.domain.models.workspace.WorkspaceSectionType


class WorkspaceSectionMemory: WorkspaceSectionStorage {

    companion object {

        private var sectionSelected_ = WorkspaceSectionType.TASKS

        private val workspaceSectionList_ = listOf(
            WorkspaceSectionDto(
                type = WorkspaceSectionType.PROJECTS,
                resourceDrawableName = ResourceName.WORKSPACE_SECTION_PROJECTS,
                categories = setOf(
                    WorkspaceSectionCategory.WORKSPACES_ALL,
                    WorkspaceSectionCategory.WORKSPACE,
                )
            ),
            WorkspaceSectionDto(
                type = WorkspaceSectionType.TASKS,
                resourceDrawableName = ResourceName.WORKSPACE_SECTION_TASKS,
                categories = setOf(
                    WorkspaceSectionCategory.WORKSPACES_ALL,
                    WorkspaceSectionCategory.WORKSPACE,
                    WorkspaceSectionCategory.PROJECT,
                )
            ),
            WorkspaceSectionDto(
                type = WorkspaceSectionType.USERS,
                resourceDrawableName = ResourceName.WORKSPACE_SECTION_USERS,
                categories = setOf(
                    WorkspaceSectionCategory.WORKSPACE,
                    WorkspaceSectionCategory.PROJECT,
                )
            ),
            WorkspaceSectionDto(
                type = WorkspaceSectionType.ACTIONS,
                resourceDrawableName = ResourceName.WORKSPACE_SECTION_ACTIONS,
                categories = setOf(
                    WorkspaceSectionCategory.WORKSPACES_ALL,
                    WorkspaceSectionCategory.WORKSPACE,
                    WorkspaceSectionCategory.PROJECT,
                )
            ),
        )
    }

    override fun getSelected(): WorkspaceSectionType {
        return sectionSelected_
    }

    override fun getDistance(
        sectionTypeFrom: WorkspaceSectionType,
        sectionTypeTo: WorkspaceSectionType,
        category: WorkspaceSectionCategory
    ): Int {
        val sectionList = getSectionList(workspaceSectionCategory = category)
        var posFrom: Int? = null
        var posTo: Int? = null

        for (i in sectionList.indices) {
            if (sectionList[i].type == sectionTypeFrom) {
                posFrom = i
            }
            if (sectionList[i].type == sectionTypeTo) {
                posTo = i
            }
        }

        if (posFrom == null || posTo == null) {
            return 0
        }

        return posTo - posFrom
    }

    override fun getSectionList(
        workspaceSectionCategory: WorkspaceSectionCategory
    ): List<WorkspaceSection> {

        val result = arrayListOf<WorkspaceSection>()
        for (workspaceSection in workspaceSectionList_) {
            if (workspaceSectionCategory in workspaceSection.categories) {
                result.add(WorkspaceSectionMapper.toDomain(
                    workspaceSectionDto = workspaceSection,
                    isSelected = workspaceSection.type == sectionSelected_
                ))
            }
        }
        return result.toList()
    }

    override fun saveSelected(workspaceSectionType: WorkspaceSectionType) {
        sectionSelected_ = workspaceSectionType
    }

}