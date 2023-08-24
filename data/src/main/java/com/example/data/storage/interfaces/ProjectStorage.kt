package com.example.data.storage.interfaces

import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Project

interface ProjectStorage {

    fun createProject(
        workspaceId: Long,
        name: String,
        description: String?,
        isPrivate: Boolean,
        imageAvatarName: String?,
        imageCoverName: String?,
        status: Status?
    )

    fun updateProject(
        projectId: Long,
        name: String?,
        description: String?,
        isPrivate: Boolean?,
        imageAvatarName: String?,
        imageCoverName: String?,
        status: Status?
    )

    fun getProject(
        projectId: Long,
        status: Status?
    ): Project?

    fun getProjectList(
        workspaceId: Long,
        format: String?,
        count: Int?,
        offset: Int,
        status: Status?
    ): List<Project>?

    fun deleteProject(
        projectId: Long,
        status: Status?
    )

}