package com.example.domain.usecase.workspace.sections

import com.example.domain.models.workspace.WorkspaceSectionType
import com.example.domain.repository.WorkspaceSectionRepository
import javax.inject.Inject

class SaveWorkspaceSectionSelectedUseCase @Inject constructor(
    val workspaceSectionRepository: WorkspaceSectionRepository) {

    fun execute(workspaceSectionType: WorkspaceSectionType) {
        return workspaceSectionRepository.saveSelected(workspaceSectionType)
    }
}