package com.example.data.models

import com.example.domain.models.ResourceName
import com.example.domain.models.WorkspaceSectionCategory
import com.example.domain.models.WorkspaceSectionType

class WorkspaceSectionDto(
    var type: WorkspaceSectionType,
    var resourceDrawableName: ResourceName,
    var categories: Set<WorkspaceSectionCategory>
)