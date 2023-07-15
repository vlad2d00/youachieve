package com.example.youachieve.presentation.adapters.listeners

import com.example.domain.models.Project

interface ProjectActionListener {
    fun onSelect(project: Project)
    fun onSettings(project: Project)
}