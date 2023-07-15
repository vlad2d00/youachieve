package com.example.data.repository

import com.example.data.mapper.ProjectMapper
import com.example.data.storage.interfaces.ProjectStorage
import com.example.domain.models.Project
import com.example.domain.models.Status
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

    override fun getProject(projectId: Long,
                            status: Status?
    ): Project? {
        val result = projectStorage.getProject(
            projectId = projectId,
            status = status
        ) ?: return null
        return ProjectMapper.toDomain(result)
    }

    override fun getProjectList(
        workspaceId: Long,
        format: String?,
        status: Status?
    ): List<Project>? {
        val items = projectStorage.getProjectList(
            workspaceId = workspaceId,
            format = format,
            status = status
        ) ?: return null

        val result = ArrayList<Project>()
        for (item in items) {
            result.add(ProjectMapper.toDomain(item))
        }
        return result.toList()
    }

    override fun deleteProject(projectId: Long,
                               status: Status?
    ) {
        projectStorage.deleteProject(
            projectId = projectId,
            status = status
        )
    }
}