package com.example.domain.models.workspace

import com.example.domain.models.base.ResourceName

class WorkspaceSection(
    var type: WorkspaceSectionType,
    var resourceName: ResourceName,
    var isSelected: Boolean
)