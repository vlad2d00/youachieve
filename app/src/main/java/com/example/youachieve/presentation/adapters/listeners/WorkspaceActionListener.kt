package com.example.youachieve.presentation.adapters.listeners

import com.example.domain.models.Workspace

interface WorkspaceActionListener {
    fun onSelect(workspace: Workspace)
    fun onSettings(workspace: Workspace)
}