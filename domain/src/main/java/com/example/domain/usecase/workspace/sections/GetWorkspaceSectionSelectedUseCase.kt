package com.example.domain.usecase.workspace.sections

import com.example.domain.models.WorkspaceSectionType
import com.example.domain.repository.WorkspaceSectionRepository
import javax.inject.Inject

class GetWorkspaceSectionSelectedUseCase @Inject constructor(
    val workspaceSectionRepository: WorkspaceSectionRepository) {

    fun execute(): WorkspaceSectionType {
        return workspaceSectionRepository.getSelected()
    }
}