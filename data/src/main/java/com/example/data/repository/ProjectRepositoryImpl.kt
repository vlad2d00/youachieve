package com.example.data.repository

import com.example.data.storage.interfaces.ProjectStorage
import com.example.domain.models.workspace.Project
import com.example.domain.models.base.Status
import com.example.domain.repository.ProjectRepository

class ProjectRepositoryImpl(
    private val projectStorage: ProjectStorage
): ProjectRepository {
    override fun createProject(
        workspaceId: Long,
        name: String,
        description: String?,
        isPrivate: Boolean,
        imageAvatarName: String?,
        imageCoverName: String?,
        status: Status?
    ) {
        return projectStorage.createProject(
            workspaceId = workspaceId,
            name = name,
            description = description,
            isPrivate = isPrivate,
            imageAvatarName = imageAvatarName,
            imageCoverName = imageCoverName,
            status = status
        )
    }

    override fun updateProject(
        projectId: Long,
        name: String?,
        description: String?,
        isPrivate: Boolean?,
        imageAvatarName: String?,
        imageCoverName: String?,
        status: Status?
    ) {
        return projectStorage.updateProject(
            projectId = projectId,
            name = name,
            description = description,
            isPrivate = isPrivate,
            imageAvatarName = imageAvatarName,
            imageCoverName = imageCoverName,
            status = status
        )
    }

    override fun getProject(
        projectId: Long,
        status: Status?
    ): Project? {
        return projectStorage.getProject(
            projectId = projectId,
            status = status
        )
    }

    override fun getProjectList(
        workspaceId: Long,
        format: String?,
        count: Int?,
        offset: Int,
        status: Status?
    ): List<Project>? {
        return projectStorage.getProjectList(
            workspaceId = workspaceId,
            format = format,

            count = count,
            offset = offset,
            status = status
        )
    }

    override fun deleteProject(
        projectId: Long,
        status: Status?
    ) {
        projectStorage.deleteProject(
            projectId = projectId,
            status = status
        )
    }
}