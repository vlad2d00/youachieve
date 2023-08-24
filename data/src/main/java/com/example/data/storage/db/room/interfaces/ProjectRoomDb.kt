package com.example.data.storage.db.room.interfaces

import android.content.Context
import com.example.data.storage.db.room.DatabaseRoom
import com.example.data.storage.db.room.entities.ProjectRoomEntity
import com.example.data.storage.db.room.entities.WorkspaceRoomEntity
import com.example.data.storage.db.room.mapper.ProjectRoomMapper
import com.example.data.storage.db.room.mapper.TaskRoomMapper
import com.example.data.storage.interfaces.ProjectStorage
import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Project
import kotlin.math.min

class ProjectRoomDb(context: Context): ProjectStorage {

    companion object {
        private const val COUNT_DEFAULT = 10
    }

    private val projectDao = DatabaseRoom.get(context).getProjectDao()
    private val workspaceDao = DatabaseRoom.get(context).getWorkspaceDao()


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

    override fun getProject(projectId: Long, status: Status?): Project? {
        val project = projectDao.getProjectById(projectId) ?: return null
        val workspace = workspaceDao.getWorkspaceById(project.workspaceId)

        return ProjectRoomMapper.toDomain(projectRoomEntity = project, workspaceName = workspace!!.name)
    }

    override fun getProjectList(
        workspaceId: Long,
        format: String?,
        count: Int?,
        offset: Int,
        status: Status?
    ): List<Project>? {
        val projectList = if (workspaceId == 0L) {
            projectDao.getProjectListAll() ?: return null
            // TODO Когда будут реализованы операции с пользователем, изменить
//            projectDao.getProjectListByUserId(workspaceId) ?: return null
        } else {
            projectDao.getProjectListByWorkspaceId(workspaceId) ?: return null
        }

        val result = arrayListOf<Project>()
        val countResult = count ?: COUNT_DEFAULT
        val formatResult = format?.trim() ?: ""
        val workspaceMap = mutableMapOf<Long, WorkspaceRoomEntity>()

        for (i in offset..min(projectList.size - 1, countResult + offset)) {
            if (formatResult != "" &&
                (projectList[i].name.findAnyOf(listOf(formatResult), 0, true) != null ||
                        projectList[i].description.findAnyOf(listOf(formatResult), 0, true) != null)) {
                continue
            }

            var workspace = workspaceMap[projectList[i].workspaceId]
            if (workspace == null) {
                workspace = workspaceDao.getWorkspaceById(projectList[i].workspaceId)
                workspaceMap[projectList[i].workspaceId] = workspace!!
            }

            result.add(ProjectRoomMapper.toDomain(
                projectRoomEntity = projectList[i],
                workspaceName = workspace.name)
            )
        }

        return result.toList()
    }

    override fun deleteProject(projectId: Long, status: Status?) {
        projectDao.deleteProjectsAll()
//        projectDao.deleteProjectById(projectId)
    }
}

