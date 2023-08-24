package com.example.domain.usecase.workspace.workspace

import com.example.domain.models.base.Status
import com.example.domain.repository.WorkspaceRepository
import javax.inject.Inject

class UpdateWorkspaceUseCase @Inject constructor(
    val workspaceRepository: WorkspaceRepository
) {
    fun execute(workspaceId: Long,
                shortName: String? = null,
                name: String? = null,
                description: String? = null,
                isPrivate: Boolean? = null,
                imageAvatarName: String? = null,
                imageCoverName: String? = null,
                status: Status? = null
    ) {
        return workspaceRepository.updateWorkspace(
            workspaceId = workspaceId,
            shortName = shortName,
            name = name,
            description = description,
            isPrivate = isPrivate,
            imageAvatarName = imageAvatarName,
            imageCoverName = imageCoverName,
            status = status
        )
    }
}