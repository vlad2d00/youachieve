package com.example.data.mapper

import com.example.data.models.TaskDto
import com.example.domain.models.Task

class TaskMapper {

    companion object {

        fun toStorage(task: Task): TaskDto {
            return TaskDto(
                id = task.id,
                name = task.name,
                description = task.description,
                datetimeBegin = task.datetimeBegin,
                datetimeEnd = task.datetimeEnd,
                imageCoverName = task.imageCoverName,
                isComplete = task.isComplete,
            )
        }

        fun toDomain(taskDto: TaskDto): Task {
            return Task(
                id = taskDto.id,
                name = taskDto.name,
                description = taskDto.description,
                imageCoverName = taskDto.imageCoverName,
                datetimeBegin = taskDto.datetimeBegin,
                datetimeEnd = taskDto.datetimeEnd,
                isComplete = taskDto.isComplete,
            )
        }

    }
}