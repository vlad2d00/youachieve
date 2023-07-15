package com.example.data.mapper

import com.example.data.models.WorkspaceDto
import com.example.domain.models.Workspace

class WorkspaceMapper {

    companion object {

        fun toStorage(workspace: Workspace): WorkspaceDto {
            return WorkspaceDto(
                id = workspace.id,
                shortName = workspace.shortName,
                name = workspace.name,
                description = workspace.description,
                isPrivate = workspace.isPrivate,
                imageAvatarName = workspace.imageAvatarName,
                imageCoverName = workspace.imageCoverName,
            )
        }

        fun toDomain(workspaceDto: WorkspaceDto): Workspace {
            return Workspace(
                id = workspaceDto.id,
                shortName = workspaceDto.shortName,
                name = workspaceDto.name,
                description = workspaceDto.description,
                isPrivate = workspaceDto.isPrivate,
                imageAvatarName = workspaceDto.imageAvatarName,
                imageCoverName = workspaceDto.imageCoverName,
            )
        }

    }
}