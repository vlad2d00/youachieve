package com.example.domain.usecase.workspace.task

import com.example.domain.models.base.Status
import com.example.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    val taskRepository: TaskRepository) {

    fun execute(taskId: Long,
                status: Status? = null
    ) {
        return taskRepository.deleteTask(
            taskId = taskId,
            status = status
        )
    }
}