package com.example.domain.usecase.workspace.project

import com.example.domain.models.Status
import com.example.domain.models.Project
import com.example.domain.repository.ProjectRepository
import javax.inject.Inject

class GetProjectListUseCase @Inject constructor(
    val projectRepository: ProjectRepository
) {
    fun execute(workspaceId: Long,
                format: String? = null,
                status: Status? = null
    ): List<Project>? {
        return projectRepository.getProjectList(
            workspaceId = workspaceId,
            format = format,
            status = status
        )
    }
}