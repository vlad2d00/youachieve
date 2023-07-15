package com.example.data.storage.db.room.mapper

import com.example.data.models.WorkspaceDto
import com.example.data.storage.db.room.entities.WorkspaceRoomEntity

class WorkspaceMapperRoom {

    companion object {

        fun toDto(workspaceRoomEntity: WorkspaceRoomEntity): WorkspaceDto {
            return WorkspaceDto(
                id = workspaceRoomEntity.id,
                shortName = workspaceRoomEntity.shortName,
                name = workspaceRoomEntity.name,
                description = workspaceRoomEntity.description,
                isPrivate = workspaceRoomEntity.isPrivate,
                imageAvatarName = workspaceRoomEntity.imageAvatarName,
                imageCoverName = workspaceRoomEntity.imageCoverName,
            )
        }

    }

}