package com.example.youachieve.presentation.adapters.listeners

import com.example.domain.models.WorkspaceSection

interface WorkspaceSectionActionListener {
    fun onSelect(workspaceSection: WorkspaceSection)
}