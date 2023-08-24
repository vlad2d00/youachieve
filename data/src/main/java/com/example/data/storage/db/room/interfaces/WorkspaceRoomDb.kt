package com.example.data.storage.db.room.interfaces

import android.content.Context
import com.example.data.storage.db.room.DatabaseRoom
import com.example.data.storage.db.room.entities.WorkspaceRoomEntity
import com.example.data.storage.db.room.mapper.WorkspaceRoomMapper
import com.example.data.storage.interfaces.WorkspaceStorage
import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Workspace
import kotlin.math.min

class WorkspaceRoomDb(context: Context): WorkspaceStorage {

    companion object {
        private const val COUNT_DEFAULT = 10
    }

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

    override fun getWorkspace(workspaceId: Long, status: Status?): Workspace? {
        val workspace = workspaceDao.getWorkspaceById(workspaceId) ?: return null
        return WorkspaceRoomMapper.toDomain(workspace)
    }

    override fun getWorkspaceList(
        userId: Long,
        format: String?,
        count: Int?,
        offset: Int,
        status: Status?
    ): List<Workspace>? {
        val workspaceList = if (userId == 0L) {
            workspaceDao.getWorkspaceListAll() ?: return null
            // TODO Когда будут реализованы операции с пользователем, изменить
//            workspaceDao.getWorkspaceListByUserId(userId) ?: return null
        } else {
            workspaceDao.getWorkspaceListByUserId(userId) ?: return null
        }

        val result = arrayListOf<Workspace>()
        val countResult = count ?: COUNT_DEFAULT
        val formatResult = format?.trim() ?: ""

        for (i in offset..min(workspaceList.size - 1, countResult + offset)) {
            if (formatResult != "" &&
                (workspaceList[i].name.findAnyOf(listOf(formatResult), 0, true) != null ||
                        workspaceList[i].description.findAnyOf(listOf(formatResult), 0, true) != null)) {
                continue
            }
            result.add(WorkspaceRoomMapper.toDomain(workspaceList[i]))
        }

        return result.toList()
    }

    override fun deleteWorkspace(workspaceId: Long, status: Status?) {
        workspaceDao.deleteWorkspacesAll()
//        workspaceDao.deleteWorkspaceById(workspaceId)
    }

}

