package com.example.data.storage.db.room.interfaces

import android.content.Context
import com.example.data.models.WorkspaceDto
import com.example.data.storage.db.room.DatabaseRoom
import com.example.data.storage.db.room.entities.WorkspaceRoomEntity
import com.example.data.storage.db.room.mapper.WorkspaceMapperRoom
import com.example.data.storage.interfaces.WorkspaceStorage
import com.example.domain.models.Status

class WorkspaceDbRoom(context: Context): WorkspaceStorage {

    private val workspaceDao = DatabaseRoom.get(context).getWorkspaceDao()


    override fun createWorkspace(name: String,
                                 description: String?,
                                 isPrivate: Boolean,
                                 imageAvatarName: String?,
                                 imageCoverName: String?,
                                 status: Status?
    ) {
        workspaceDao.insertWorkspace(
            WorkspaceRoomEntity(
                id = 0,
                shortName = name,
                name = name,
                description = description ?: "",
                isPrivate = isPrivate,
                imageAvatarName = imageAvatarName ?: "",
                imageCoverName = imageCoverName ?: "",
            )
        )
    }

    override fun updateWorkspace(workspaceId: Long,
                                 shortName: String?,
                                 name: String?,
                                 description: String?,
                                 isPrivate: Boolean?,
                                 imageAvatarName: String?,
                                 imageCoverName: String?,
                                 status: Status?
    ) {
        val workspace = workspaceDao.getWorkspaceById(workspaceId) ?: return

        if (shortName != null)
            workspace.shortName = shortName

        if (name != null)
            workspace.name = name

        if (description != null)
            workspace.description = description

        if (imageAvatarName != null)
            workspace.imageAvatarName = imageAvatarName

        if (imageCoverName != null)
            workspace.imageCoverName = imageCoverName

        workspaceDao.updateWorkspace(workspace)
    }

    override fun getWorkspace(workspaceId: Long, status: Status?): WorkspaceDto? {
        val workspace = workspaceDao.getWorkspaceById(workspaceId) ?: return null
        return WorkspaceMapperRoom.toDto(workspace)
    }

    override fun getWorkspaceList(userId: Long?, format: String?, status: Status?): List<WorkspaceDto>? {
        val items = if (userId == null) {
            workspaceDao.getWorkspaceListAll() ?: return null
        } else {
            workspaceDao.getWorkspaceListByUserId(userId) ?: return null
        }

        val result = arrayListOf<WorkspaceDto>()
        val formatNew = format?.trim() ?: ""

        for (item in items) {
            if (formatNew != "" &&
                (item.name.findAnyOf(listOf(formatNew), 0, true) != null ||
                        item.description.findAnyOf(listOf(formatNew), 0, true) != null)) {
                continue
            }
            result.add(WorkspaceMapperRoom.toDto(item))
        }

        return result.toList()
    }

    override fun deleteWorkspace(workspaceId: Long, status: Status?) {
        workspaceDao.deleteWorkspacesAll()
//        workspaceDao.deleteWorkspaceById(workspaceId)
    }

}

