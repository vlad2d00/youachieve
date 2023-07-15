package com.example.domain.repository

import com.example.domain.models.WorkspaceSection
import com.example.domain.models.WorkspaceSectionCategory
import com.example.domain.models.WorkspaceSectionType

interface WorkspaceSectionRepository {
    fun getSelected(): WorkspaceSectionType
    fun getSectionList(workspaceSectionCategory: WorkspaceSectionCategory): List<WorkspaceSection>
    fun saveSelected(workspaceSectionType: WorkspaceSectionType)
}