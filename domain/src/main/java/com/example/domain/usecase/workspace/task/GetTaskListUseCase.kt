package com.example.domain.usecase.workspace.task

import com.example.domain.models.Status
import com.example.domain.models.Task
import com.example.domain.repository.TaskRepository
import java.time.LocalDateTime
import javax.inject.Inject

class GetTaskListUseCase @Inject constructor(
    val taskRepository: TaskRepository
) {
    fun execute(projectId: Long,
                format: String? = null,
                datetimeBegin: LocalDateTime? = null,
                datetimeEnd: LocalDateTime? = null,
                isComplete: Boolean? = null,
                status: Status? = null
    ): List<Task>? {
        return taskRepository.getTaskList(
            projectId = projectId,
            format = format,
            datetimeBegin = datetimeBegin,
            datetimeEnd = datetimeEnd,
            isComplete = isComplete,
            status = status
        )
    }
}