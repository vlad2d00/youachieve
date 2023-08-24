package com.example.data.storage.memory.models

import com.example.domain.models.base.ResourceName
import com.example.domain.models.workspace.WorkspaceSectionCategory
import com.example.domain.models.workspace.WorkspaceSectionType

class WorkspaceSectionDto(
    var type: WorkspaceSectionType,
    var resourceDrawableName: ResourceName,
    var categories: Set<WorkspaceSectionCategory>
)