package com.example.data.storage.db.room.mapper

import com.example.data.storage.db.room.entities.TaskRoomEntity
import com.example.domain.models.workspace.Task
import com.example.domain.utils.DatetimeService

class TaskRoomMapper {

    companion object {

        fun toDomain(taskRoomEntity: TaskRoomEntity, projectName: String): Task {
            return Task(
                id = taskRoomEntity.id,
                name = taskRoomEntity.name,
                description = taskRoomEntity.description,
                datetimeBegin = if (taskRoomEntity.datetimeBegin == "") null else
                    DatetimeService.toDatetime(taskRoomEntity.datetimeBegin),
                datetimeEnd = if (taskRoomEntity.datetimeEnd == "") null else
                    DatetimeService.toDatetime(taskRoomEntity.datetimeEnd),
                imageCoverName = taskRoomEntity.imageCoverName,
                isComplete = taskRoomEntity.isComplete,

                projectId = taskRoomEntity.projectId,
                projectName = projectName
            )
        }

    }

}