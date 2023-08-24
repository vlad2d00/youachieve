package com.example.domain.usecase.workspace.project

import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Project
import com.example.domain.repository.ProjectRepository
import javax.inject.Inject

class GetProjectListUseCase @Inject constructor(
    val projectRepository: ProjectRepository) {

    fun execute(workspaceId: Long,
                format: String? = null,
                count: Int? = null,
                offset: Int = 0,
                status: Status? = null
    ): List<Project>? {
        return projectRepository.getProjectList(
            workspaceId = workspaceId,
            format = format,
            count = count,
            offset = offset,
            status = status
        )
    }
}