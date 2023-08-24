package com.example.data.storage.memory.mapper

import com.example.data.storage.memory.models.WorkspaceSectionDto
import com.example.domain.models.workspace.WorkspaceSection

class WorkspaceSectionMapper {

    companion object {

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