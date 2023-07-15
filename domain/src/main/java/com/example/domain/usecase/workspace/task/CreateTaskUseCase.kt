package com.example.domain.usecase.workspace.task

import com.example.domain.models.Status
import com.example.domain.repository.TaskRepository
import java.time.LocalDateTime
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    val taskRepository: TaskRepository
) {
    fun execute(projectId: Long,
                name: String,
                description: String? = null,
                imageCoverName: String? = null,
                datetimeBegin: LocalDateTime? = null,
                datetimeEnd: LocalDateTime? = null,
                status: Status? = null
    ) {
        return taskRepository.createTask(
            projectId = projectId,
            name = name,
            description = description,
            imageCoverName = imageCoverName,
            datetimeBegin = datetimeBegin,
            datetimeEnd = datetimeEnd,
            status = status
        )
    }
}