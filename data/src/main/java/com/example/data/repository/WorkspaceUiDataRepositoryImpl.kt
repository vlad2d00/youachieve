package com.example.data.repository

import com.example.data.storage.interfaces.WorkspaceUiDataStorage
import com.example.domain.models.WorkspaceSectionCategory
import com.example.domain.repository.WorkspaceUiDataRepository

class WorkspaceUiDataRepositoryImpl(
    private val workspaceUiDataStorage: WorkspaceUiDataStorage
): WorkspaceUiDataRepository {

    override fun getWorkspaceIdSelected(): Long {
        return workspaceUiDataStorage.getWorkspaceIdSelected()
    }

    override fun getProjectIdSelected(): Long {
        return workspaceUiDataStorage.getProjectIdSelected()
    }

    override fun saveWorkspaceIdSelected(workspaceId: Long) {
        workspaceUiDataStorage.saveWorkspaceIdSelected(workspaceId = workspaceId)
    }

    override fun saveProjectIdSelected(projectId: Long) {
        workspaceUiDataStorage.saveProjectIdSelected(projectId = projectId)
    }

    override fun getCategory(): WorkspaceSectionCategory {
        return workspaceUiDataStorage.getCategory()
    }
}