package com.example.domain.usecase.workspace.task

import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Task
import com.example.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    val taskRepository: TaskRepository) {

    fun execute(taskId: Long,
                status: Status? = null
    ): Task? {
        return taskRepository.getTask(
            taskId = taskId,
            status = status
        )
    }
}