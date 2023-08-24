package com.example.data.storage.memory

import com.example.data.storage.interfaces.WorkspaceUiDataStorage
import com.example.domain.models.workspace.WorkspaceSectionCategory
import com.example.domain.models.workspace.WorkspaceSectionType

class WorkspaceUiDataMemory: WorkspaceUiDataStorage {

    companion object {
        private var projectIdSelected_: Long = 0L
        private var workspaceIdSelected_: Long = 0L
    }

    override fun getWorkspaceIdSelected(): Long {
        return workspaceIdSelected_
    }

    override fun getProjectIdSelected(): Long {
        return projectIdSelected_
    }

    override fun saveWorkspaceIdSelected(workspaceId: Long) {
        workspaceIdSelected_ = workspaceId
        updateSectionSelected()
    }

    override fun saveProjectIdSelected(projectId: Long) {
        projectIdSelected_ = projectId
        updateSectionSelected()
    }

    override fun getCategory(): WorkspaceSectionCategory {
        return if (workspaceIdSelected_ == 0L) {
            WorkspaceSectionCategory.WORKSPACES_ALL
        } else {
            if (projectIdSelected_ == 0L)
                WorkspaceSectionCategory.WORKSPACE
            else
                WorkspaceSectionCategory.PROJECT
        }
    }

    private fun updateSectionSelected() {
        val workspaceSectionMemory = WorkspaceSectionMemory()
        val category = getCategory()
        val sectionList = workspaceSectionMemory.getSectionList(workspaceSectionCategory = category)

        var f = false
        for (section in sectionList) {
            if (section.type == workspaceSectionMemory.getSelected()) {
                f = true
            }
        }
        if (!f) {
            workspaceSectionMemory.saveSelected(workspaceSectionType =
            when (category) {
                WorkspaceSectionCategory.WORKSPACES_ALL -> WorkspaceSectionType.PROJECTS
                WorkspaceSectionCategory.WORKSPACE -> WorkspaceSectionType.PROJECTS
                WorkspaceSectionCategory.PROJECT -> WorkspaceSectionType.TASKS
            }
            )
        }
    }

}