package com.example.data.mapper

import com.example.data.models.ProjectDto
import com.example.domain.models.Project

class ProjectMapper {

    companion object {

        fun toStorage(project: Project): ProjectDto {
            return ProjectDto(
                id = project.id,
                name = project.name,
                description = project.description,
                isPrivate = project.isPrivate,
                imageAvatarName = project.imageAvatarName,
                imageCoverName = project.imageCoverName,
            )
        }

        fun toDomain(projectDto: ProjectDto): Project {
            return Project(
                id = projectDto.id,
                name = projectDto.name,
                description = projectDto.description,
                isPrivate = projectDto.isPrivate,
                imageAvatarName = projectDto.imageAvatarName,
                imageCoverName = projectDto.imageCoverName,
            )
        }

    }
}