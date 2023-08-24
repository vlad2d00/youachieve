package com.example.domain.usecase.workspace.sections

import com.example.domain.models.workspace.WorkspaceSectionCategory
import com.example.domain.models.workspace.WorkspaceSectionType
import com.example.domain.repository.WorkspaceSectionRepository
import javax.inject.Inject

class GetWorkspaceSectionDistanceUseCase @Inject constructor(
    val workspaceSectionRepository: WorkspaceSectionRepository) {

    fun execute(
        sectionTypeFrom: WorkspaceSectionType,
        sectionTypeTo: WorkspaceSectionType,
        category: WorkspaceSectionCategory
    ): Int {
        return workspaceSectionRepository.getDistance(
            sectionTypeFrom = sectionTypeFrom,
            sectionTypeTo = sectionTypeTo,
            category = category
        )
    }
}