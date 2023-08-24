package com.example.domain.usecase.workspace.sections

import com.example.domain.models.workspace.WorkspaceSectionCategory
import com.example.domain.repository.WorkspaceUiDataRepository
import javax.inject.Inject

class GetWorkspaceSectionCategoryUseCase @Inject constructor(
    val workspaceUiDataRepository: WorkspaceUiDataRepository) {

    fun execute(): WorkspaceSectionCategory {
        return workspaceUiDataRepository.getCategory()
    }
}