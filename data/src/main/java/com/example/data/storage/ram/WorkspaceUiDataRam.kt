package com.example.data.storage.ram

import com.example.data.storage.interfaces.WorkspaceUiDataStorage
import com.example.domain.models.WorkspaceSectionCategory
import com.example.domain.models.WorkspaceSectionType

class WorkspaceUiDataRam: WorkspaceUiDataStorage {

    companion object {
        private var projectIdSelected: Long = 0L
        private var workspaceIdSelected: Long = 0L
    }

    override fun getWorkspaceIdSelected(): Long {
        return workspaceIdSelected
    }

    override fun getProjectIdSelected(): Long {
        return projectIdSelected
    }

    override fun saveWorkspaceIdSelected(workspaceId: Long) {
        workspaceIdSelected = workspaceId
        updateSectionSelected()
    }

    override fun saveProjectIdSelected(projectId: Long) {
        projectIdSelected = projectId
        updateSectionSelected()
    }

    override fun getCategory(): WorkspaceSectionCategory {
        return if (workspaceIdSelected == 0L) {
            WorkspaceSectionCategory.WORKSPACES_ALL
        } else {
            if (projectIdSelected == 0L)
                WorkspaceSectionCategory.WORKSPACE
            else
                WorkspaceSectionCategory.PROJECT
        }
    }

    private fun updateSectionSelected() {
        val workspaceSectionRam = WorkspaceSectionRam()
        val category = getCategory()
        val sectionList = workspaceSectionRam.getSectionList(workspaceSectionCategory = category)

        var f = false
        for (section in sectionList) {
            if (section.type == workspaceSectionRam.getSelected()) {
                f = true
            }
        }
        if (!f) {
            workspaceSectionRam.saveSelected(workspaceSectionType =
            when (category) {
                WorkspaceSectionCategory.WORKSPACES_ALL -> WorkspaceSectionType.WORKSPACES
                WorkspaceSectionCategory.WORKSPACE -> WorkspaceSectionType.PROJECTS
                WorkspaceSectionCategory.PROJECT -> WorkspaceSectionType.TASKS
            }
            )
        }
    }

}