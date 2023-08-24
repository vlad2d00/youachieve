package com.example.youachieve.presentation.adapters.listeners

import com.example.domain.models.workspace.TaskGroup

interface TaskGroupActionListener {
    fun onSpoiler(taskGroup: TaskGroup, isOpen: Boolean)
}