package com.example.domain.usecase.workspace.sections

import com.example.domain.repository.WorkspaceUiDataRepository
import javax.inject.Inject

class SaveWorkspaceIdSelectedUseCase @Inject constructor(
    val workspaceUiDataRepository: WorkspaceUiDataRepository) {

    fun execute(workspaceId: Long) {
        return workspaceUiDataRepository.saveWorkspaceIdSelected(workspaceId)
    }
}