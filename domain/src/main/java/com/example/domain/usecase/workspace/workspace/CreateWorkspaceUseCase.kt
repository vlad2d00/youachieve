package com.example.domain.usecase.workspace.workspace

import com.example.domain.models.base.Status
import com.example.domain.repository.WorkspaceRepository
import javax.inject.Inject

class CreateWorkspaceUseCase @Inject constructor(
    val workspaceRepository: WorkspaceRepository) {

    fun execute(name: String,
                description: String? = null,
                isPrivate: Boolean,
                imageAvatarName: String? = null,
                imageCoverName: String? = null,
                status: Status? = null
    ) {
        return workspaceRepository.createWorkspace(
            name = name,
            description = description,
            isPrivate = isPrivate,
            imageAvatarName = imageAvatarName,
            imageCoverName = imageCoverName,
            status = status
        )
    }
}