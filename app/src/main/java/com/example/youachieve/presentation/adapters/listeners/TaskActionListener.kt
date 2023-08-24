package com.example.youachieve.presentation.adapters.listeners

import com.example.domain.models.workspace.Task
import com.example.youachieve.presentation.adapters.TaskAdapter

interface TaskActionListener {
    fun onSelect(task: Task)
    fun onUpdateStatus(task: Task, isComplete: Boolean, taskAdapter: TaskAdapter)
}