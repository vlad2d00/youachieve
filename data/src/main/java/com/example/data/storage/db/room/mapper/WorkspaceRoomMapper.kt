package com.example.data.storage.db.room.mapper

import com.example.data.storage.db.room.entities.WorkspaceRoomEntity
import com.example.domain.models.workspace.Workspace

class WorkspaceRoomMapper {

    companion object {

        fun toDomain(workspaceRoomEntity: WorkspaceRoomEntity): Workspace {
            return Workspace(
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