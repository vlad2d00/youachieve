package com.example.domain.usecase.workspace.task

import com.example.domain.models.Status
import com.example.domain.repository.TaskRepository
import java.time.LocalDateTime
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    val taskRepository: TaskRepository
) {
    fun execute(taskId: Long,
                name: String? = null,
                description: String? = null,
                imageCoverName: String? = null,
                datetimeBegin: LocalDateTime? = null,
                datetimeEnd: LocalDateTime? = null,
                isComplete: Boolean? = null,
                status: Status? = null
    ) {
        return taskRepository.updateTask(
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
}