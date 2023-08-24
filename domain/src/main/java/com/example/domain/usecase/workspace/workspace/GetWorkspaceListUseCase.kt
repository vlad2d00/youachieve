package com.example.domain.usecase.workspace.workspace

import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Workspace
import com.example.domain.repository.WorkspaceRepository
import javax.inject.Inject

class GetWorkspaceListUseCase @Inject constructor(
    val workspaceRepository: WorkspaceRepository) {

    fun execute(userId: Long = 0,
                format: String? = null,
                count: Int? = null,
                offset: Int = 0,
                status: Status? = null
    ): List<Workspace>? {
        return workspaceRepository.getWorkspaceList(
            userId = userId,
            format = format,
            count = count,
            offset = offset,
            status = status
        )
    }
}