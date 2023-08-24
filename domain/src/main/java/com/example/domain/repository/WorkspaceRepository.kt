package com.example.domain.repository

import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Workspace

interface WorkspaceRepository {

    fun createWorkspace(
        name: String,
        description: String?,
        isPrivate: Boolean,
        imageAvatarName: String?,
        imageCoverName: String?,
        status: Status?
    )

    fun updateWorkspace(
        workspaceId: Long,
        shortName: String?,
        name: String?,
        description: String?,
        isPrivate: Boolean?,
        imageAvatarName: String?,
        imageCoverName: String?,
        status: Status?
    )

    fun getWorkspace(
        workspaceId: Long,
        status: Status?
    ): Workspace?

    fun getWorkspaceList(
        userId: Long,
        format: String?,
        count: Int?,
        offset: Int,
        status: Status?
    ): List<Workspace>?

    fun deleteWorkspace(
        workspaceId: Long,
        status: Status?
    )

}