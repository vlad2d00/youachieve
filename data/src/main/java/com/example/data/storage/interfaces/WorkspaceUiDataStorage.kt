package com.example.data.storage.interfaces

import com.example.domain.models.workspace.WorkspaceSectionCategory

interface WorkspaceUiDataStorage {
    fun getWorkspaceIdSelected(): Long
    fun getProjectIdSelected(): Long
    fun getCategory(): WorkspaceSectionCategory
    fun saveWorkspaceIdSelected(workspaceId: Long)
    fun saveProjectIdSelected(projectId: Long)
}