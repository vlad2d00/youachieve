package com.example.domain.usecase.workspace.sections

import com.example.domain.repository.WorkspaceUiDataRepository
import javax.inject.Inject

class GetProjectIdSelectedUseCase @Inject constructor(
    val workspaceUiDataRepository: WorkspaceUiDataRepository) {

    fun execute(): Long {
        return workspaceUiDataRepository.getProjectIdSelected()
    }
}