package com.example.domain.repository

import com.example.domain.models.Task
import com.example.domain.models.Status
import java.time.LocalDateTime

interface TaskRepository {

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
    ): Task?

    fun getTaskList(projectId: Long,
                    format: String?,
                    datetimeBegin: LocalDateTime?,
                    datetimeEnd: LocalDateTime?,
                    isComplete: Boolean?,
                    status: Status?
    ): List<Task>?

    fun deleteTask(taskId: Long,
                   status: Status?
    )

}