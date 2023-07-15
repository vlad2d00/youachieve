package com.example.domain.usecase.workspace.project

import com.example.domain.models.Status
import com.example.domain.repository.ProjectRepository
import javax.inject.Inject

class DeleteProjectUseCase @Inject constructor(
    val projectRepository: ProjectRepository
) {
    fun execute(projectId: Long,
                status: Status? = null
    ) {
        return projectRepository.deleteProject(
            projectId = projectId,
            status = status
        )
    }
}