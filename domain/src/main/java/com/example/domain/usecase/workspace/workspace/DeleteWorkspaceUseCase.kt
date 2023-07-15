package com.example.domain.usecase.workspace.workspace

import com.example.domain.models.Status
import com.example.domain.repository.WorkspaceRepository
import javax.inject.Inject

class DeleteWorkspaceUseCase @Inject constructor(
    val workspaceRepository: WorkspaceRepository
) {
    fun execute(workspaceId: Long,
                status: Status? = null
    ) {
        return workspaceRepository.deleteWorkspace(
            workspaceId = workspaceId,
            status = status
        )
    }
}