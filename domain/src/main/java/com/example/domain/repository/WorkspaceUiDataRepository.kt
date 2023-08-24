package com.example.domain.repository

import com.example.domain.models.workspace.WorkspaceSectionCategory

interface WorkspaceUiDataRepository {
    fun getWorkspaceIdSelected(): Long
    fun getProjectIdSelected(): Long
    fun getCategory(): WorkspaceSectionCategory
    fun saveWorkspaceIdSelected(workspaceId: Long)
    fun saveProjectIdSelected(projectId: Long)
}