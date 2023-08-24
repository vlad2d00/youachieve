package com.example.youachieve.presentation.adapters.listeners

import com.example.domain.models.workspace.Project
import com.example.domain.models.workspace.Workspace

interface WorkspaceActionListener {
    fun onSelect(workspace: Workspace)
    fun onSettings(project: Project)
}