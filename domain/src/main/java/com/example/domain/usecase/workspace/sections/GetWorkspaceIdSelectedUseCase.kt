package com.example.domain.usecase.workspace.sections

import com.example.domain.repository.WorkspaceUiDataRepository
import javax.inject.Inject

class GetWorkspaceIdSelectedUseCase @Inject constructor(
    val workspaceUiDataRepository: WorkspaceUiDataRepository) {

    fun execute(): Long {
        return workspaceUiDataRepository.getWorkspaceIdSelected()
    }
}