package com.example.data.storage.db.room.mapper

import com.example.data.models.ProjectDto
import com.example.data.storage.db.room.entities.ProjectRoomEntity

class ProjectMapperRoom {

    companion object {

        fun toDto(projectRoomEntity: ProjectRoomEntity): ProjectDto {
            return ProjectDto(
                id = projectRoomEntity.id,
                name = projectRoomEntity.name,
                description = projectRoomEntity.description,
                isPrivate = projectRoomEntity.isPrivate,
                imageAvatarName = projectRoomEntity.imageAvatarName,
                imageCoverName = projectRoomEntity.imageCoverName,
            )
        }

    }

}