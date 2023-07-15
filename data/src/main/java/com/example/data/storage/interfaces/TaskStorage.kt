package com.example.data.storage.interfaces

import com.example.data.models.TaskDto
import com.example.domain.models.Status
import java.time.LocalDateTime

interface TaskStorage {

    fun createTask(projectId: Long,
                   name: String,
                   description: String?,
                   imageCoverName: String?,
                   datetimeBegin: LocalDateTime?,
                   datetimeEnd: LocalDateTime?,
                   status: Status?
    )

    fun updateTask(taskId: Long,
                   name: String?,
                   description: String?,
                   imageCoverName: String?,
                   datetimeBegin: LocalDateTime?,
                   datetimeEnd: LocalDateTime?,
                   isComplete: Boolean?,
                   status: Status?
    )

    fun getTask(taskId: Long,
                status: Status?
    ): TaskDto?

    fun getTaskList(projectId: Long,
                    format: String?,
                    datetimeBegin: LocalDateTime?,
                    datetimeEnd: LocalDateTime?,
                    isComplete: Boolean?,
                    status: Status?
    ): List<TaskDto>?

    fun deleteTask(taskId: Long,
                   status: Status?
    )

}