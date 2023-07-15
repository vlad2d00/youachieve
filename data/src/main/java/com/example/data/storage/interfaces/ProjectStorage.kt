package com.example.data.storage.interfaces

import com.example.data.models.ProjectDto
import com.example.domain.models.Status

interface ProjectStorage {

    fun createProject(workspaceId: Long,
                      name: String,
                      description: String?,
                      isPrivate: Boolean,
                      imageAvatarName: String?,
                      imageCoverName: String?,
                      status: Status?
    )

    fun updateProject(projectId: Long,
                      name: String?,
                      description: String?,
                      isPrivate: Boolean?,
                      imageAvatarName: String?,
                      imageCoverName: String?,
                      status: Status?
    )

    fun getProject(projectId: Long,
                   status: Status?
    ): ProjectDto?

    fun getProjectList(workspaceId: Long,
                       format: String?,
                       status: Status?
    ): List<ProjectDto>?

    fun deleteProject(projectId: Long,
                      status: Status?
    )

}