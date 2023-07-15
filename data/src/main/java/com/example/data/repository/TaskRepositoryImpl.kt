package com.example.data.repository

import com.example.data.mapper.TaskMapper
import com.example.data.storage.interfaces.TaskStorage
import com.example.domain.models.Status
import com.example.domain.models.Task
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

    override fun getTask(taskId: Long,
                         status: Status?
    ): Task? {
        val result = taskStorage.getTask(
            taskId = taskId,
            status = status
        ) ?: return null
        return TaskMapper.toDomain(result)
    }

    override fun getTaskList(
        projectId: Long,
        format: String?,
        datetimeBegin: LocalDateTime?,
        datetimeEnd: LocalDateTime?,
        isComplete: Boolean?,
        status: Status?
    ): List<Task>? {
        val items = taskStorage.getTaskList(
            projectId = projectId,
            format = format,
            datetimeBegin = datetimeBegin,
            datetimeEnd = datetimeEnd,
            isComplete = isComplete,
            status = status
        ) ?: return null

        val result = ArrayList<Task>()
        for (item in items) {
            result.add(TaskMapper.toDomain(item))
        }
        return result.toList()
    }

    override fun deleteTask(taskId: Long,
                            status: Status?
    ) {
        taskStorage.deleteTask(
            taskId = taskId,
            status = status
        )
    }
}