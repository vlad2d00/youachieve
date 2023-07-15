package com.example.data.mapper

import com.example.data.models.WorkspaceSectionDto
import com.example.domain.models.WorkspaceSection
import com.example.domain.models.WorkspaceSectionType

class WorkspaceSectionMapper {

    companion object {

        fun toStorage(workspaceSection: WorkspaceSection): WorkspaceSectionDto {
            return WorkspaceSectionDto(
                type = workspaceSection.type,
                resourceDrawableName = workspaceSection.resourceName,
                categories = emptySet()
            )
        }

        fun toDomain(workspaceSectionDto: WorkspaceSectionDto,
                     isSelected: Boolean
        ): WorkspaceSection {
            return WorkspaceSection(
                type = workspaceSectionDto.type,
                resourceName = workspaceSectionDto.resourceDrawableName,
                isSelected = isSelected
            )
        }

    }
}