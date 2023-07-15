package com.example.data.storage.interfaces

import com.example.data.models.WorkspaceDto
import com.example.domain.models.Status

interface WorkspaceStorage {

    fun createWorkspace(name: String,
                        description: String?,
                        isPrivate: Boolean,
                        imageAvatarName: String?,
                        imageCoverName: String?,
                        status: Status?
    )

    fun updateWorkspace(workspaceId: Long,
                        shortName: String?,
                        name: String?,
                        description: String?,
                        isPrivate: Boolean?,
                        imageAvatarName: String?,
                        imageCoverName: String?,
                        status: Status?
    )

    fun getWorkspace(workspaceId: Long,
                     status: Status?
    ): WorkspaceDto?

    fun getWorkspaceList(userId: Long?,
                         format: String?,
                         status: Status?
    ): List<WorkspaceDto>?

    fun deleteWorkspace(workspaceId: Long,
                        status: Status?
    )

}