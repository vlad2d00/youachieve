package com.example.data.storage.ram

import com.example.data.storage.interfaces.WorkspaceSectionStorage
import com.example.data.models.WorkspaceSectionDto
import com.example.domain.models.ResourceName
import com.example.domain.models.WorkspaceSectionCategory
import com.example.domain.models.WorkspaceSectionType


class WorkspaceSectionRam(): WorkspaceSectionStorage {

    companion object {
        private var sectionSelected = WorkspaceSectionType.TASKS
        private val workspaceSectionList = listOf(
            WorkspaceSectionDto(
                type = WorkspaceSectionType.WORKSPACES,
                resourceDrawableName = ResourceName.WORKSPACE_SECTION_WORKSPACE,
                categories = setOf(
                    WorkspaceSectionCategory.WORKSPACES_ALL,
                )
            ),
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
        return sectionSelected
    }

    override fun getSectionList(workspaceSectionCategory: WorkspaceSectionCategory): List<WorkspaceSectionDto> {
        return getWorkspaceSectionModelListByCategory(workspaceSectionCategory = workspaceSectionCategory)
    }

    override fun saveSelected(workspaceSectionType: WorkspaceSectionType) {
        sectionSelected = workspaceSectionType
    }

    private fun getWorkspaceSectionModelListByCategory(
        workspaceSectionCategory: WorkspaceSectionCategory): List<WorkspaceSectionDto> {

        val result = arrayListOf<WorkspaceSectionDto>()
        for (item in workspaceSectionList) {
            if (workspaceSectionCategory in item.categories)
                result.add(item)
        }
        return result.toList()
    }

}