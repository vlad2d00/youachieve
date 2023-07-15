package com.example.domain.usecase.workspace.workspace

import com.example.domain.models.Status
import com.example.domain.models.Workspace
import com.example.domain.repository.WorkspaceRepository
import javax.inject.Inject

class GetWorkspaceListUseCase @Inject constructor(
    val workspaceRepository: WorkspaceRepository
) {
    fun execute(userId: Long? = null,
                format: String? = null,
                status: Status? = null
    ): List<Workspace>? {
        return workspaceRepository.getWorkspaceList(
            userId = userId,
            format = format,
            status = status
        )
    }
}