package com.example.domain.usecase.workspace.workspace

import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Workspace
import com.example.domain.repository.WorkspaceRepository
import javax.inject.Inject

class GetWorkspaceUseCase @Inject constructor(
    val workspaceRepository: WorkspaceRepository) {

    fun execute(workspaceId: Long,
                status: Status? = null
    ): Workspace? {
        return workspaceRepository.getWorkspace(
            workspaceId = workspaceId,
            status = status
        )
    }
}