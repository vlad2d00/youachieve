package com.example.data.storage.db.room.mapper

import com.example.data.storage.db.room.entities.ProjectRoomEntity
import com.example.domain.models.workspace.Project

class ProjectRoomMapper {

    companion object {

        fun toDomain(projectRoomEntity: ProjectRoomEntity, workspaceName: String): Project {
            return Project(
                id = projectRoomEntity.id,
                name = projectRoomEntity.name,
                description = projectRoomEntity.description,
                isPrivate = projectRoomEntity.isPrivate,
                imageAvatarName = projectRoomEntity.imageAvatarName,
                imageCoverName = projectRoomEntity.imageCoverName,

                workspaceId = projectRoomEntity.workspaceId,
                workspaceName = workspaceName
            )
        }

    }

}