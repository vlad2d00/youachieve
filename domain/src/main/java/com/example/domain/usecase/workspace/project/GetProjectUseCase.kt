package com.example.domain.usecase.workspace.project

import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Project
import com.example.domain.repository.ProjectRepository
import javax.inject.Inject

class GetProjectUseCase @Inject constructor(
    val projectRepository: ProjectRepository) {

    fun execute(projectId: Long,
                status: Status? = null
    ): Project? {
        return projectRepository.getProject(
            projectId = projectId,
            status = status
        )
    }
}