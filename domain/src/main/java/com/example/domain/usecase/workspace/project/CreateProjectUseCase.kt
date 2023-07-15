package com.example.domain.usecase.workspace.project

import com.example.domain.models.Status
import com.example.domain.repository.ProjectRepository
import javax.inject.Inject

class CreateProjectUseCase @Inject constructor(
    val projectRepository: ProjectRepository
) {
    fun execute(workspaceId: Long,
                name: String,
                description: String? = null,
                isPrivate: Boolean,
                imageAvatarName: String? = null,
                imageCoverName: String? = null,
                status: Status? = null
    ) {
        return projectRepository.createProject(
            workspaceId = workspaceId,
            name = name,
            description = description,
            isPrivate = isPrivate,
            imageAvatarName = imageAvatarName,
            imageCoverName = imageCoverName,
            status = status
        )
    }
}