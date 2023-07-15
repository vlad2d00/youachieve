package com.example.domain.usecase.workspace.sections

import com.example.domain.models.WorkspaceSection
import com.example.domain.models.WorkspaceSectionCategory
import com.example.domain.repository.WorkspaceSectionRepository
import javax.inject.Inject

class GetWorkspaceSectionListUseCase @Inject constructor(
    val workspaceSectionRepository: WorkspaceSectionRepository
    ) {

    fun execute(workspaceSectionCategoryType: WorkspaceSectionCategory): List<WorkspaceSection> {
        return workspaceSectionRepository.getSectionList(workspaceSectionCategoryType)
    }
}