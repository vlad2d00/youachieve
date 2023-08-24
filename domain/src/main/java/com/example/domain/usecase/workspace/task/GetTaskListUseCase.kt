package com.example.domain.usecase.workspace.task

import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Task
import com.example.domain.repository.TaskRepository
import java.time.LocalDateTime
import javax.inject.Inject

class GetTaskListUseCase @Inject constructor(
    val taskRepository: TaskRepository) {

    fun execute(workspaceId: Long = 0,
                projectId: Long = 0,
                format: String? = null,
                datetimeBegin: LocalDateTime? = null,
                datetimeEnd: LocalDateTime? = null,
                isComplete: Boolean? = null,
                count: Int? = null,
                offset: Int = 0,
                status: Status? = null
    ): List<Task>? {
        return taskRepository.getTaskList(
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
}