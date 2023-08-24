package com.example.data.repository

import com.example.data.storage.interfaces.TaskStorage
import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Task
import com.example.domain.repository.TaskRepository
import java.time.LocalDateTime

class TaskRepositoryImpl(
    private val taskStorage: TaskStorage
): TaskRepository {
    override fun createTask(
        projectId: Long,
        name: String,
        description: String?,
        imageCoverName: String?,
        datetimeBegin: LocalDateTime?,
        datetimeEnd: LocalDateTime?,
        status: Status?
    ) {
        return taskStorage.createTask(
            projectId = projectId,
            name = name,
            description = description,
            imageCoverName = imageCoverName,
            datetimeBegin = datetimeBegin,
            datetimeEnd = datetimeEnd,
            status = status
        )
    }

    override fun updateTask(
        taskId: Long,
        name: String?,
        description: String?,
        imageCoverName: String?,
        datetimeBegin: LocalDateTime?,
        datetimeEnd: LocalDateTime?,
        isComplete: Boolean?,
        status: Status?
    ) {
        return taskStorage.updateTask(
            taskId = taskId,
            name = name,
            description = description,
            imageCoverName = imageCoverName,
            datetimeBegin = datetimeBegin,
            datetimeEnd = datetimeEnd,
            isComplete = isComplete,
            status = status
        )
    }

    override fun getTask(
        taskId: Long,
        status: Status?
    ): Task? {
        return taskStorage.getTask(
            taskId = taskId,
            status = status
        )
    }

    override fun getTaskList(
        workspaceId: Long,
        projectId: Long,
        format: String?,
        datetimeBegin: LocalDateTime?,
        datetimeEnd: LocalDateTime?,
        isComplete: Boolean?,
        count: Int?,
        offset: Int,
        status: Status?
    ): List<Task>? {
        return taskStorage.getTaskList(
            workspaceId = workspaceId,
            projectId = projectId,
            format = format,
            datetimeBegin = datetimeBegin,
            datetimeEnd = datetimeEnd,
            isComplete = isComplete,

            count = count,
            offset = offset,
            status = status
        )
    }

    override fun deleteTask(
        taskId: Long,
        status: Status?
    ) {
        taskStorage.deleteTask(
            taskId = taskId,
            status = status
        )
    }
}