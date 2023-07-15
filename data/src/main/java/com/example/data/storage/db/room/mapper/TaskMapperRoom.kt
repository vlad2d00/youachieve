package com.example.data.storage.db.room.mapper

import com.example.data.models.TaskDto
import com.example.data.models.WorkspaceDto
import com.example.data.storage.db.room.entities.TaskRoomEntity
import com.example.data.storage.db.room.entities.WorkspaceRoomEntity
import com.example.domain.utils.Datetime

class TaskMapperRoom {

    companion object {

        fun toDto(taskRoomEntity: TaskRoomEntity): TaskDto {
            return TaskDto(
                id = taskRoomEntity.id,
                name = taskRoomEntity.name,
                description = taskRoomEntity.description,
                datetimeBegin = if (taskRoomEntity.datetimeBegin == "") null else
                    Datetime.toDatetime(taskRoomEntity.datetimeBegin),
                datetimeEnd = if (taskRoomEntity.datetimeEnd == "") null else
                    Datetime.toDatetime(taskRoomEntity.datetimeEnd),
                imageCoverName = taskRoomEntity.imageCoverName,
                isComplete = taskRoomEntity.isComplete,
            )
        }

    }

}