package com.example.domain.repository

import com.example.domain.models.workspace.Task
import com.example.domain.models.base.Status
import java.time.LocalDateTime

interface TaskRepository {

    fun createTask(
        projectId: Long,
        name: String,
        description: String?,
        imageCoverName: String?,
        datetimeBegin: LocalDateTime?,
        datetimeEnd: LocalDateTime?,
        status: Status?
    )

    fun updateTask(
        taskId: Long,
        name: String?,
        description: String?,
        imageCoverName: String?,
        datetimeBegin: LocalDateTime?,
        datetimeEnd: LocalDateTime?,
        isComplete: Boolean?,
        status: Status?)

    fun getTask(
        taskId: Long,
        status: Status?
    ): Task?

    fun getTaskList(
        workspaceId: Long,
        projectId: Long,
        format: String?,
        datetimeBegin: LocalDateTime?,
        datetimeEnd: LocalDateTime?,
        isComplete: Boolean?,
        count: Int?,
        offset: Int,
        status: Status?
    ): List<Task>?

    fun deleteTask(
        taskId: Long,
        status: Status?
    )

}