package com.example.data.storage.db.room.interfaces

import android.content.Context
import com.example.data.models.ProjectDto
import com.example.data.models.WorkspaceDto
import com.example.data.storage.db.room.DatabaseRoom
import com.example.data.storage.db.room.entities.ProjectRoomEntity
import com.example.data.storage.db.room.entities.WorkspaceRoomEntity
import com.example.data.storage.db.room.mapper.ProjectMapperRoom
import com.example.data.storage.db.room.mapper.WorkspaceMapperRoom
import com.example.data.storage.interfaces.ProjectStorage
import com.example.domain.models.Status

class ProjectDbRoom(context: Context): ProjectStorage {

    private val projectDao = DatabaseRoom.get(context).getProjectDao()


    override fun createProject(
        workspaceId: Long,
        name: String,
        description: String?,
        isPrivate: Boolean,
        imageAvatarName: String?,
        imageCoverName: String?,
        status: Status?
    ) {
        val obj = ProjectRoomEntity(
            id = 0,
            name = name,
            description = description ?: "",
            isPrivate = isPrivate,
            imageAvatarName = imageAvatarName ?: "",
            imageCoverName = imageCoverName ?: "",
            workspaceId = workspaceId,
        )
        projectDao.insertProject(
            obj
        )
    }

    override fun updateProject(
        projectId: Long,
        name: String?,
        description: String?,
        isPrivate: Boolean?,
        imageAvatarName: String?,
        imageCoverName: String?,
        status: Status?
    ) {
        val project = projectDao.getProjectById(projectId) ?: return

        if (name != null)
            project.name = name

        if (description != null)
            project.description = description

        if (isPrivate != null)
            project.isPrivate = isPrivate

        if (imageAvatarName != null)
            project.imageAvatarName = imageAvatarName

        if (imageCoverName != null)
            project.imageCoverName = imageCoverName

        projectDao.updateProject(project)
    }

    override fun getProject(projectId: Long, status: Status?): ProjectDto? {
        val project = projectDao.getProjectById(projectId) ?: return null
        return ProjectMapperRoom.toDto(project)
    }

    override fun getProjectList(
        workspaceId: Long,
        format: String?,
        status: Status?
    ): List<ProjectDto>? {
        val items = projectDao.getProjectListByWorkspaceId(workspaceId) ?: return null
        val result = arrayListOf<ProjectDto>()
        val formatNew = format?.trim() ?: ""

        val all = projectDao.getProjectListAll()

        for (item in items) {
            if (formatNew != "" &&
                (item.name.findAnyOf(listOf(formatNew), 0, true) != null ||
                        item.description.findAnyOf(listOf(formatNew), 0, true) != null)) {
                continue
            }
            result.add(ProjectMapperRoom.toDto(item))
        }

        val allno = projectDao.getProjectListByWorkspaceId(48)

        return result.toList()
    }

    override fun deleteProject(projectId: Long, status: Status?) {
        projectDao.deleteProjectsAll()
//        projectDao.deleteProjectById(projectId)
    }
}

