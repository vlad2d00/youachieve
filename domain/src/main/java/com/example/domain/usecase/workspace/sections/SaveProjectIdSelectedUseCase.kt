package com.example.domain.usecase.workspace.sections

import com.example.domain.repository.WorkspaceUiDataRepository
import javax.inject.Inject

class SaveProjectIdSelectedUseCase @Inject constructor(
    val workspaceUiDataRepository: WorkspaceUiDataRepository) {

    fun execute(projectId: Long) {
        return workspaceUiDataRepository.saveProjectIdSelected(projectId)
    }
}