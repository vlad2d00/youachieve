package com.example.data.repository

import com.example.data.mapper.WorkspaceMapper
import com.example.data.storage.interfaces.WorkspaceStorage
import com.example.domain.models.Status
import com.example.domain.models.Workspace
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

    override fun getWorkspace(workspaceId: Long,
                              status: Status?
    ): Workspace? {
        val result = workspaceStorage.getWorkspace(
            workspaceId = workspaceId,
            status = status
        ) ?: return null
        return WorkspaceMapper.toDomain(result)
    }

    override fun getWorkspaceList(userId: Long?,
                                  format: String?,
                                  status: Status?
    ): List<Workspace>? {
        val items = workspaceStorage.getWorkspaceList(
            userId = userId,
            format = format,
            status = status
        ) ?: return null

        val result = ArrayList<Workspace>()

        for (item in items) {
            result.add(WorkspaceMapper.toDomain(item))
        }
        return result.toList()
    }

    override fun deleteWorkspace(workspaceId: Long,
                                 status: Status?
    ) {
        workspaceStorage.deleteWorkspace(
            workspaceId = workspaceId,
            status = status
        )
    }
}