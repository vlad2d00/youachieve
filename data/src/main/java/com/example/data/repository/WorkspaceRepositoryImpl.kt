package com.example.data.repository

import com.example.data.storage.interfaces.WorkspaceStorage
import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Workspace
import com.example.domain.repository.WorkspaceRepository

class WorkspaceRepositoryImpl(
    private val workspaceStorage: WorkspaceStorage
): WorkspaceRepository {

    override fun createWorkspace(
        name: String,
        description: String?,
        isPrivate: Boolean,
        imageAvatarName: String?,
        imageCoverName: String?,
        status: Status?
    ) {
        return workspaceStorage.createWorkspace(
            name = name,
            description = description,
            isPrivate = isPrivate,
            imageAvatarName = imageAvatarName,
            imageCoverName = imageCoverName,
            status = status
        )
    }

    override fun updateWorkspace(
        workspaceId: Long,
        shortName: String?,
        name: String?,
        description: String?,
        isPrivate: Boolean?,
        imageAvatarName: String?,
        imageCoverName: String?,
        status: Status?
    ) {
        return workspaceStorage.updateWorkspace(
            workspaceId = workspaceId,
            shortName = shortName,
            name = name,
            description = description,
            isPrivate = isPrivate,
            imageAvatarName = imageAvatarName,
            imageCoverName = imageCoverName,
            status = status
        )
    }

    override fun getWorkspace(
        workspaceId: Long,
        status: Status?
    ): Workspace? {
        return workspaceStorage.getWorkspace(
            workspaceId = workspaceId,
            status = status
        )
    }

    override fun getWorkspaceList(
        userId: Long,
        format: String?,
        count: Int?,
        offset: Int,
        status: Status?
    ): List<Workspace>? {
        return workspaceStorage.getWorkspaceList(
            userId = userId,
            format = format,

            count = count,
            offset = offset,
            status = status
        )
    }

    override fun deleteWorkspace(
        workspaceId: Long,
        status: Status?
    ) {
        workspaceStorage.deleteWorkspace(
            workspaceId = workspaceId,
            status = status
        )
    }
}