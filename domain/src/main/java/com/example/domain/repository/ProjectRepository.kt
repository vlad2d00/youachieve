package com.example.domain.repository

import com.example.domain.models.Project
import com.example.domain.models.Status

interface ProjectRepository {

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
    ): Project?

    fun getProjectList(workspaceId: Long,
                       format: String?,
                       status: Status?
    ): List<Project>?

    fun deleteProject(projectId: Long,
                      status: Status?
    )

}